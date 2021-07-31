package com.message.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.sql.Blob;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ahuwhq
 * @since 2021-07-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "userId", type = IdType.AUTO)
    private Integer userId;

    @TableField("userName")
    private String userName;

    @TableField("userPassword")
    private String userPassword;

    @TableField("currentVersion")
    private String currentVersion;

    @TableField("latestVersion")
    private String latestVersion;

    @TableField("updateDescription")
    private String updateDescription;

    @TableField("headPortrait")
    private Blob headPortrait;

    @TableField("nickName")
    private String nickName;

    @TableField("vipTime")
    private String vipTime;

    @TableField("userCategory")
    private String userCategory;

    @TableField("registerDate")
    private String registerDate;


}
