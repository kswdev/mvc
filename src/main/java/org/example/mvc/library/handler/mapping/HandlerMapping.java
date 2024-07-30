package org.example.mvc.library.handler.mapping;

public interface HandlerMapping {

    Object findHandler(HandlerKey handlerKey);
}
