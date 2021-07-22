package io.github.shuoding.ast;

import java.util.List;
import java.util.ArrayList;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.ParserRuleContext;
import io.github.shuoding.grammar.*;

public class ASTConstructionVisitor extends SMTLIBv2BaseVisitor<AST> {

	private String getTextOfContext(ParserRuleContext ctx) {
		return ctx.start.getInputStream().getText(new Interval(ctx.start.getStartIndex(), ctx.stop.getStopIndex()));
	}

	@Override
	public AST visitStart(SMTLIBv2Parser.StartContext ctx) {
		List<SMTLIBv2Parser.CommandContext> commandContextList = ctx.script().command();
		ArrayList<ASTCommand> commandList = new ArrayList<>();
		for (SMTLIBv2Parser.CommandContext commandContext : commandContextList) {
			commandList.add((ASTCommand)visit(commandContext));
		}
		return new ASTStart(commandList);
	}

	@Override
	public AST visitSetLogicCommand(SMTLIBv2Parser.SetLogicCommandContext ctx) {
		String symbol = getTextOfContext(ctx.cmd_setLogic().symbol());
		return new ASTSetLogicCommand(symbol);
	}

	@Override
	public AST visitDeclConstCommand(SMTLIBv2Parser.DeclConstCommandContext ctx) {
		String symbol = getTextOfContext(ctx.cmd_declareConst().symbol());
		String sort = getTextOfContext(ctx.cmd_declareConst().sort());
		return new ASTDeclConstCommand(symbol, sort);
	}

	@Override
	public AST visitDeclFunCommand(SMTLIBv2Parser.DeclFunCommandContext ctx) {
		String symbol = getTextOfContext(ctx.cmd_declareFun().symbol());
		List<SMTLIBv2Parser.SortContext> sortContextList = ctx.cmd_declareFun().sort();
		int lastIndex = sortContextList.size() - 1;
		String returnSort = getTextOfContext(sortContextList.get(lastIndex));
		sortContextList.remove(lastIndex);
		ArrayList<String> parameterSortList = new ArrayList<>();
		for (SMTLIBv2Parser.SortContext sortContext : sortContextList) {
			parameterSortList.add(getTextOfContext(sortContext));
		}
		return new ASTDeclFunCommand(symbol, parameterSortList, returnSort);
	}

	@Override
	public AST visitAssertCommand(SMTLIBv2Parser.AssertCommandContext ctx) {
		ASTTerm term = (ASTTerm)visit(ctx.cmd_assert().term());
		return new ASTAssertCommand(term);
	}

	@Override
	public AST visitDefFunCommand(SMTLIBv2Parser.DefFunCommandContext ctx) {
		SMTLIBv2Parser.Function_defContext funDefCtx = ctx.cmd_defineFun().function_def();
		String symbol = getTextOfContext(funDefCtx.symbol());
		List<SMTLIBv2Parser.Sorted_varContext> sortedVarContextList = funDefCtx.sorted_var();
		ArrayList<String> parameterSymbolList = new ArrayList<>();
		ArrayList<String> parameterSortList = new ArrayList<>();
		for (SMTLIBv2Parser.Sorted_varContext sortedVarContext : sortedVarContextList) {
			parameterSymbolList.add(getTextOfContext(sortedVarContext.symbol()));
			parameterSortList.add(getTextOfContext(sortedVarContext.sort()));
		}
		String sort = getTextOfContext(funDefCtx.sort());
		ASTTerm term = (ASTTerm)visit(funDefCtx.term());
		return new ASTDefFunCommand(symbol, parameterSymbolList, parameterSortList, sort, term);
	}

	@Override
	public AST visitOtherCommand(SMTLIBv2Parser.OtherCommandContext ctx) {
		return new ASTOtherCommand(getTextOfContext(ctx));
	}

	@Override
	public AST visitConstTerm(SMTLIBv2Parser.ConstTermContext ctx) {
		String spec_constant = getTextOfContext(ctx.spec_constant());
		return new ASTConstTerm(spec_constant);
	}

	@Override
	public AST visitIDTerm(SMTLIBv2Parser.IDTermContext ctx) {
		String qual_identifier = getTextOfContext(ctx.qual_identifier());
		return new ASTIDTerm(qual_identifier);
	}

	@Override
	public AST visitAppTerm(SMTLIBv2Parser.AppTermContext ctx) {
		String qual_identifier = getTextOfContext(ctx.qual_identifier());
		List<SMTLIBv2Parser.TermContext> termContextList = ctx.term();
		ArrayList<ASTTerm> termList = new ArrayList<>();
		for (SMTLIBv2Parser.TermContext termContext : termContextList) {
			termList.add((ASTTerm)visit(termContext));
		}
		return new ASTAppTerm(qual_identifier, termList);
	}

	@Override
	public AST visitLetTerm(SMTLIBv2Parser.LetTermContext ctx) {
		List<SMTLIBv2Parser.Var_bindingContext> varBindingContextList = ctx.var_binding();
		ArrayList<String> symbolList = new ArrayList<>();
		ArrayList<ASTTerm> termList = new ArrayList<>();
		for (SMTLIBv2Parser.Var_bindingContext varBindingContext : varBindingContextList) {
			symbolList.add(getTextOfContext(varBindingContext.symbol()));
			termList.add((ASTTerm)visit(varBindingContext.term()));
		}
		ASTTerm term = (ASTTerm)visit(ctx.term());
		return new ASTLetTerm(symbolList, termList, term);
	}

	@Override
	public AST visitForallTerm(SMTLIBv2Parser.ForallTermContext ctx) {
		List<SMTLIBv2Parser.Sorted_varContext> sortedVarContextList = ctx.sorted_var();
		ArrayList<String> symbolList = new ArrayList<>();
		ArrayList<String> sortList = new ArrayList<>();
		for (SMTLIBv2Parser.Sorted_varContext sortedVarContext : sortedVarContextList) {
			symbolList.add(getTextOfContext(sortedVarContext.symbol()));
			sortList.add(getTextOfContext(sortedVarContext.sort()));
		}
		ASTTerm term = (ASTTerm)visit(ctx.term());
		return new ASTForallTerm(symbolList, sortList, term);
	}

	@Override
	public AST visitExistsTerm(SMTLIBv2Parser.ExistsTermContext ctx) {
		List<SMTLIBv2Parser.Sorted_varContext> sortedVarContextList = ctx.sorted_var();
		ArrayList<String> symbolList = new ArrayList<>();
		ArrayList<String> sortList = new ArrayList<>();
		for (SMTLIBv2Parser.Sorted_varContext sortedVarContext : sortedVarContextList) {
			symbolList.add(getTextOfContext(sortedVarContext.symbol()));
			sortList.add(getTextOfContext(sortedVarContext.sort()));
		}
		ASTTerm term = (ASTTerm)visit(ctx.term());
		return new ASTExistsTerm(symbolList, sortList, term);
	}

	@Override
	public AST visitExclamationTerm(SMTLIBv2Parser.ExclamationTermContext ctx) {
		ASTTerm term = (ASTTerm)visit(ctx.term());
		List<SMTLIBv2Parser.AttributeContext> attributeContextList = ctx.attribute();
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (SMTLIBv2Parser.AttributeContext attributeContext : attributeContextList) {
			String attributeText = getTextOfContext(attributeContext);
			if (first) {
				sb.append(attributeText);
				first = false;
			} else {
				sb.append(" ");
				sb.append(attributeText);
			}
		}
		return new ASTExclamationTerm(term, sb.toString());
	}
}
