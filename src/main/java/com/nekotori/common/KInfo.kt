package com.nekotori.common

import io.netty.buffer.Unpooled

import io.netty.channel.ChannelFuture

import io.netty.channel.ChannelHandlerContext

import io.netty.util.CharsetUtil

object KInfo {

    fun echo(ctx: ChannelHandlerContext, info: String):ChannelFuture{

        return ctx.writeAndFlush(Unpooled.copiedBuffer(info, CharsetUtil.UTF_8))
    }

}