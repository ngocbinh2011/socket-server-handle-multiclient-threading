package socket.server.impl;

import socket.server.exception.CommandNotFoundException;

public enum Command {
    SAVE_PRODUCT("saveproduct"),
    DELETE_BY_ID("deletebyid"),
    FIND_BY_ID("findbyid"),
    FIND_BY_NAME("findbyname"),
    EDIT_PRICE("editprice"),
    QUIT("quit");

    private String value;

    Command(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Command getCommand(String command) throws CommandNotFoundException {
        Command[] cmds = Command.values();
        Command result = null;
        for(Command c: cmds){
            if(c.getValue().equals(command)){
                result = c;
            }
        }
        if(result == null){
            throw new CommandNotFoundException(String.format("command %s not supported!", command));
        }
        return result;
    }
}
