package com.apiSystem.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 要记录项目动态的service类要添加该注解
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ProjectAlterLog {}
