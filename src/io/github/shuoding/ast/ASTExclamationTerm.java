package io.github.shuoding.ast;

public class ASTExclamationTerm extends ASTTerm {

	public ASTTerm term;
	public String attributeListText;

	public ASTExclamationTerm(ASTTerm term, String attributeListText) {
		this.term = term;
		this.attributeListText = attributeListText;
	}

	@Override
	public String print() {
		StringBuilder sb = new StringBuilder();
		sb.append("(! ");
		sb.append(term.print());
		sb.append(" ");
		sb.append(attributeListText);
		sb.append(")");
		return sb.toString();
	}
}
