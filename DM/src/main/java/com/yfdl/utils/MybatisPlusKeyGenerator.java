package com.yfdl.utils;
import com.baomidou.mybatisplus.annotation.DbType;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("mybatisPlusKeyGenerator")
public class MybatisPlusKeyGenerator implements IKeyGenerator {

    @Override
    public String executeSql(String incrementerName) {
        log.info("执行自定义Key生成器，参数：{}",incrementerName);
        return "SELECT GLOBAL_SEQ.NEXTVAL FROM DUAL";
    }

    @Override
    public DbType dbType() {
        return null;
    }
}
