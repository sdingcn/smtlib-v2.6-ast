package io.github.shuoding.ast;

import java.util.ArrayList;

public class ASTStart extends AST {
	
	public ArrayList<ASTCommand> commandList;

	public ASTStart(ArrayList<ASTCommand> commandList) {
		this.commandList = commandList;
	}

	public String print() {
		StringBuilder sb = new StringBuilder();
		for (ASTCommand command : commandList) {
			sb.append(command.print());
			sb.append('\n');
		}
		return sb.toString();
	}
}
