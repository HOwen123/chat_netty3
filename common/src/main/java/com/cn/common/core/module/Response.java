package com.cn.common.core.module;

public class Response {

    /**
     * 模块号
     */
    private short module;

    /**
     * 命令号
     */
    private short cmd;

    /**
     * 结果码
     */
    private int stateCode = ResultCode.SUCCESS;

    /**
     * 数据
     */
    private byte[] data;

    public short getModule() {
        return module;
    }

    public void setModule(short module) {
        this.module = module;
    }

    public short getCmd() {
        return cmd;
    }

    public void setCmd(short cmd) {
        this.cmd = cmd;
    }

    public int getStateCode() {
        return stateCode;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Response(){

    }

    public Response(Response message){
        this.module = message.getModule();
        this.cmd = message.getCmd();
    }

    public Response(short module,short cmd,byte[] data){
        this.module = module;
        this.cmd = cmd;
        this.data = data;
    }

}
