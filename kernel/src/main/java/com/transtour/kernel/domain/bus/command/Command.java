package com.transtour.kernel.domain.bus.command;

import java.io.Serializable;
import java.util.HashMap;

public interface Command {
    HashMap<String, Serializable> toPrimitives();
}
