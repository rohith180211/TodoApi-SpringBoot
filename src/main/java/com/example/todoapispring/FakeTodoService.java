package com.example.todoapispring;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("faketodoservice")
public class FakeTodoService implements TodoService{
    @TimeMonitor
    public String doSomething(){
        for(int i=0;i<100000;i++){}
        return "Do Something";
    }
}
