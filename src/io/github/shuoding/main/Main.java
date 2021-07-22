package io.github.shuoding.main;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import io.github.shuoding.ast.*;
import io.github.shuoding.grammar.*;

public class Main {

	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			String usage = "Available Command Line Arguments:\n"
				     + "    pretty-print\n";
			System.err.print(usage);
		} else {
			SMTLIBv2Lexer lexer = new SMTLIBv2Lexer(CharStreams.fromStream(System.in));
			SMTLIBv2Parser parser = new SMTLIBv2Parser(new CommonTokenStream(lexer));
			SMTLIBv2Parser.StartContext tree = parser.start(); // the concrete syntax tree
			ASTStart start = (ASTStart)((new ASTConstructionVisitor()).visit(tree)); // the abstract syntax tree
			String option = args[0];
			if (option.equals("pretty-print")) {
				System.out.print(start.print());
			}
		}
	}
}
