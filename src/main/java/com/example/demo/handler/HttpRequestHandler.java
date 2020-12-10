package com.example.demo.handler;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedNioFile;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;

public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final String wsUri;

    private static final File INDEX;

    static {
        URL location = HttpRequestHandler.class
                .getProtectionDomain()
                .getCodeSource().getLocation();
        try {
            String path = location.toURI() + "index.html";
            path=!path.contains("file:") ? path:path.substring(5);
            INDEX=new File(path);
        } catch (URISyntaxException e) {
           throw new IllegalStateException("Unable to locate index.html",e);
        }


    }

    public HttpRequestHandler(String wsUri) {
        this.wsUri = wsUri;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext cxt, FullHttpRequest request) throws Exception {
        if(wsUri.equalsIgnoreCase(request.getUri())){
            cxt.fireChannelRead(request.retain());
        }
        RandomAccessFile file = new RandomAccessFile(INDEX, "r");
        DefaultHttpResponse response = new DefaultHttpResponse(request.getProtocolVersion(), HttpResponseStatus.OK);
        response.headers().set(HttpHeaders.Names.CONTENT_TYPE,"text/plain;charset=UTF-8");
        boolean keepAlive = HttpHeaders.isKeepAlive(request);
        if(!keepAlive){
            response.headers().set(HttpHeaders.Names.CONTENT_LENGTH,file.length());
            response.headers().set(HttpHeaders.Names.CONNECTION,HttpHeaders.Values.KEEP_ALIVE);
        }
        cxt.write(response);
        if(cxt.pipeline().get(SslHandler.class)==null){
            cxt.write(new DefaultFileRegion(file.getChannel(),0,file.length()));
        }else {
            cxt.write(new ChunkedNioFile(file.getChannel()));
        }
        ChannelFuture future = cxt.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
        if(!keepAlive){
            future.addListener(ChannelFutureListener.CLOSE);
        }

    }

    private static void send100Continue(ChannelHandlerContext ctx){
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE);
        ctx.writeAndFlush(response);
        ;

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
