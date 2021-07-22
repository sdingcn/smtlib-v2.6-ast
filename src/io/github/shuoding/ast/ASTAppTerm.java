package io.github.shuoding.ast;

import java.util.ArrayList;

public class ASTAppTerm extends ASTTerm {

	public String qual_identifier;
	public ArrayList<ASTTerm> termList;

	public ASTAppTerm(String qual_identifier, ArrayList<ASTTerm> termList) {
		this.qual_identifier = qual_identifier;
		this.termList = termList;
	}

	@Override
	public String print() {
		StringBuilder sb = new StringBuilder();
		sb.append('(');
		sb.append(qual_identifier);
		for (ASTTerm term : termList) {
			sb.append(' ');
			sb.append(term.print());
		}
		sb.append(')');
		return sb.toString();
	}
}
