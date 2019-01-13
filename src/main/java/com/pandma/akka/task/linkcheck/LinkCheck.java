package com.pandma.akka.task.linkcheck;

import com.pandma.akka.task.Result;

public interface LinkCheck {
    Result check() throws Exception;
}
