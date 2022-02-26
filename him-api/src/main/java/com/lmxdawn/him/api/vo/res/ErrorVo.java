package com.lmxdawn.him.api.vo.res;

import com.lmxdawn.him.common.vo.req.BaseReqVO;
import com.lmxdawn.him.common.vo.req.MessageType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class ErrorVo extends BaseReqVO {
    @NotNull(message = "参数错误~")
    private String errorContext;

    public ErrorVo(String errorContext) {
        isType();
        this.errorContext = errorContext;
    }

    @Override
    public void isType() {
        setType(MessageType.ERROR);
    }
}
