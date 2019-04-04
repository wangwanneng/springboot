package com.wwn.test;

import com.wwn.App;
import com.wwn.mq.Send;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class SpringRabbitTest {

    @Autowired
    private Send send;

    @Test
    public void testRabbitmq(){
        send.send();
    }
}
