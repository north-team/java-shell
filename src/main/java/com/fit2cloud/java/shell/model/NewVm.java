package com.fit2cloud.java.shell.model;

import io.swagger.annotations.ApiModelProperty;

public class NewVm {
    @ApiModelProperty("名字")
    private String name;
    @ApiModelProperty("模板路径")
    private String vhdPath;
    @ApiModelProperty("版本号")
    private String generation;
    @ApiModelProperty("内存")
    private String memoryStartupBytes;
    @ApiModelProperty("网络")
    private String switchName;
    @ApiModelProperty("存储地址")
    private String path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVhdPath() {
        return vhdPath;
    }

    public void setVhdPath(String vhdPath) {
        this.vhdPath = vhdPath;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public String getMemoryStartupBytes() {
        return memoryStartupBytes;
    }

    public void setMemoryStartupBytes(String memoryStartupBytes) {
        this.memoryStartupBytes = memoryStartupBytes;
    }

    public String getSwitchName() {
        return switchName;
    }

    public void setSwitchName(String switchName) {
        this.switchName = switchName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
