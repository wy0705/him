package com.lmxdawn.him.api.vo.res;

import com.lmxdawn.him.common.vo.req.BaseReqVO;
import com.lmxdawn.him.common.vo.req.MessageType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class URLVo extends BaseReqVO {


    @NotNull(message = "参数错误~")
    @Length(min = 1, max = 255, message = "参数错误~")
    private String url;

    public URLVo(String url) {
        isType();
        this.url = url;
    }


    @Override
    public void isType() {
        setType(MessageType.URL);
    }
}
