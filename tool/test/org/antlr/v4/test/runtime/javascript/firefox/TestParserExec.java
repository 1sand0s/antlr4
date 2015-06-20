package org.antlr.v4.test.runtime.javascript.firefox;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

@SuppressWarnings("unused")
public class TestParserExec extends BaseTest {

	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testEOFInClosure() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(53);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("prog : stat EOF;\n");
		grammarBuilder.append("stat : 'x' ('y' | EOF)*?;");
		String grammar = grammarBuilder.toString();
		String input ="x";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "prog", input, false);
		assertEquals("", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testParserProperty() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(208);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("@members {\n");
		grammarBuilder.append("this.Property = function() {\n");
		grammarBuilder.append("    return true;\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("a : {$parser.Property()}? ID {document.getElementById('output').value += \"valid\" + '\\n';}\n");
		grammarBuilder.append("  ;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="abc";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "a", input, false);
		assertEquals("valid\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testPredictionIssue334() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(306);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("file_ @init{\n");
		grammarBuilder.append("this._errHandler = new antlr4.error.BailErrorStrategy();\n");
		grammarBuilder.append("} \n");
		grammarBuilder.append("@after {\n");
		grammarBuilder.append("document.getElementById('output').value += $ctx.toStringTree(null, this) + '\\n';\n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("  :   item (SEMICOLON item)* SEMICOLON? EOF ;\n");
		grammarBuilder.append("item : A B?;\n");
		grammarBuilder.append("SEMICOLON: ';';\n");
		grammarBuilder.append("A : 'a'|'A';\n");
		grammarBuilder.append("B : 'b'|'B';\n");
		grammarBuilder.append("WS      : [ \\r\\t\\n]+ -> skip;");
		String grammar = grammarBuilder.toString();
		String input ="a";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "file_", input, false);
		assertEquals("(file_ (item a) <EOF>)\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testListLabelsOnSet() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(140);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : b b* ';' ;\n");
		grammarBuilder.append("b : ID val+=(INT | FLOAT)*;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+;\n");
		grammarBuilder.append("FLOAT : [0-9]+ '.' [0-9]+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="abc 34;";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "a", input, false);
		assertEquals("", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testPredicatedIfIfElse() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(175);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("s : stmt EOF ;\n");
		grammarBuilder.append("stmt : ifStmt | ID;\n");
		grammarBuilder.append("ifStmt : 'if' ID stmt ('else' stmt | { this._input.LA(1)!=TParser.ELSE }?);\n");
		grammarBuilder.append("ELSE : 'else';\n");
		grammarBuilder.append("ID : [a-zA-Z]+;\n");
		grammarBuilder.append("WS : [ \\n\\t]+ -> skip;");
		String grammar = grammarBuilder.toString();
		String input ="if x if x a else b";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "s", input, true);
		assertEquals("", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testIfIfElseGreedyBinding2() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(230);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("start : statement+ ;\n");
		grammarBuilder.append("statement : 'x' | ifStatement;\n");
		grammarBuilder.append("ifStatement : 'if' 'y' statement ('else' statement|) {\n");
		grammarBuilder.append("document.getElementById('output').value += $text + '\\n';\n");
		grammarBuilder.append("};\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> channel(HIDDEN);");
		String grammar = grammarBuilder.toString();
		String input ="if y if y x else x";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "start", input, false);
		assertEquals(
			"if y x else x\n" +
			"if y if y x else x\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testLabelAliasingAcrossLabeledAlternatives() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(245);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("start : a* EOF;\n");
		grammarBuilder.append("a\n");
		grammarBuilder.append("  : label=subrule {document.getElementById('output').value += $label.text + '\\n';} #One\n");
		grammarBuilder.append("  | label='y' {document.getElementById('output').value += $label.text + '\\n';} #Two\n");
		grammarBuilder.append("  ;\n");
		grammarBuilder.append("subrule : 'x';\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="xy";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "start", input, false);
		assertEquals(
			"x\n" +
			"y\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testAorBPlus() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(149);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : (ID|INT{\n");
		grammarBuilder.append("})+ {\n");
		grammarBuilder.append("document.getElementById('output').value += $text + '\\n';\n");
		grammarBuilder.append("};\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="a 34 c";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "a", input, false);
		assertEquals("a34c\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testLL1OptionalBlock_1() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(147);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : (ID|{}INT)? {\n");
		grammarBuilder.append("document.getElementById('output').value += $text + '\\n';\n");
		grammarBuilder.append("};\n");
		grammarBuilder.append("ID : 'a'..'z'+;\n");
		grammarBuilder.append("INT : '0'..'9'+ ;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip;");
		String grammar = grammarBuilder.toString();
		String input ="";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "a", input, false);
		assertEquals("\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testLL1OptionalBlock_2() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(147);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : (ID|{}INT)? {\n");
		grammarBuilder.append("document.getElementById('output').value += $text + '\\n';\n");
		grammarBuilder.append("};\n");
		grammarBuilder.append("ID : 'a'..'z'+;\n");
		grammarBuilder.append("INT : '0'..'9'+ ;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip;");
		String grammar = grammarBuilder.toString();
		String input ="a";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "a", input, false);
		assertEquals("a\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testOptional_1() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(90);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("stat : ifstat | 'x';\n");
		grammarBuilder.append("ifstat : 'if' stat ('else' stat)?;\n");
		grammarBuilder.append("WS : [ \\n\\t]+ -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="x";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "stat", input, false);
		assertEquals("", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testOptional_2() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(90);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("stat : ifstat | 'x';\n");
		grammarBuilder.append("ifstat : 'if' stat ('else' stat)?;\n");
		grammarBuilder.append("WS : [ \\n\\t]+ -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="if x";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "stat", input, false);
		assertEquals("", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testIfIfElseGreedyBinding1() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(230);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("start : statement+ ;\n");
		grammarBuilder.append("statement : 'x' | ifStatement;\n");
		grammarBuilder.append("ifStatement : 'if' 'y' statement ('else' statement)? {\n");
		grammarBuilder.append("document.getElementById('output').value += $text + '\\n';\n");
		grammarBuilder.append("};\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> channel(HIDDEN);");
		String grammar = grammarBuilder.toString();
		String input ="if y if y x else x";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "start", input, false);
		assertEquals(
			"if y x else x\n" +
			"if y if y x else x\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testLabels() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(118);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : b1=b b2+=b* b3+=';' ;\n");
		grammarBuilder.append("b : id_=ID val+=INT*;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="abc 34;";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "a", input, false);
		assertEquals("", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testOptional_3() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(90);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("stat : ifstat | 'x';\n");
		grammarBuilder.append("ifstat : 'if' stat ('else' stat)?;\n");
		grammarBuilder.append("WS : [ \\n\\t]+ -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="if x else x";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "stat", input, false);
		assertEquals("", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testOptional_4() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(90);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("stat : ifstat | 'x';\n");
		grammarBuilder.append("ifstat : 'if' stat ('else' stat)?;\n");
		grammarBuilder.append("WS : [ \\n\\t]+ -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="if if x else x";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "stat", input, false);
		assertEquals("", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testAorAPlus() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(126);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : (ID|ID)+ {\n");
		grammarBuilder.append("document.getElementById('output').value += $text + '\\n';\n");
		grammarBuilder.append("};\n");
		grammarBuilder.append("ID : 'a'..'z'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip;");
		String grammar = grammarBuilder.toString();
		String input ="a b c";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "a", input, false);
		assertEquals("abc\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testAorB() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(210);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : ID {\n");
		grammarBuilder.append("document.getElementById('output').value += \"alt 1\" + '\\n';\n");
		grammarBuilder.append("} | INT {\n");
		grammarBuilder.append("document.getElementById('output').value += \"alt 2\" + '\\n';\n");
		grammarBuilder.append("};\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="34";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "a", input, false);
		assertEquals("alt 2\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testAStar_1() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(121);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : ID* {\n");
		grammarBuilder.append("document.getElementById('output').value += $text + '\\n';\n");
		grammarBuilder.append("};\n");
		grammarBuilder.append("ID : 'a'..'z'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip;");
		String grammar = grammarBuilder.toString();
		String input ="";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "a", input, false);
		assertEquals("\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testAStar_2() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(121);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : ID* {\n");
		grammarBuilder.append("document.getElementById('output').value += $text + '\\n';\n");
		grammarBuilder.append("};\n");
		grammarBuilder.append("ID : 'a'..'z'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip;");
		String grammar = grammarBuilder.toString();
		String input ="a b c";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "a", input, false);
		assertEquals("abc\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testListLabelForClosureContext() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(434);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("ifStatement\n");
		grammarBuilder.append("@after {\n");
		grammarBuilder.append("items = [].concat($ctx.elseIfStatement()); \n");
		grammarBuilder.append("}\n");
		grammarBuilder.append("    : 'if' expression\n");
		grammarBuilder.append("      ( ( 'then'\n");
		grammarBuilder.append("          executableStatement*\n");
		grammarBuilder.append("          elseIfStatement*  // <--- problem is here\n");
		grammarBuilder.append("          elseStatement?\n");
		grammarBuilder.append("          'end' 'if'\n");
		grammarBuilder.append("        ) | executableStatement )\n");
		grammarBuilder.append("    ;\n");
		grammarBuilder.append("\n");
		grammarBuilder.append("elseIfStatement\n");
		grammarBuilder.append("    : 'else' 'if' expression 'then' executableStatement*\n");
		grammarBuilder.append("    ;\n");
		grammarBuilder.append("expression : 'a' ;\n");
		grammarBuilder.append("executableStatement : 'a' ;\n");
		grammarBuilder.append("elseStatement : 'a' ;");
		String grammar = grammarBuilder.toString();
		String input ="a";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "expression", input, false);
		assertEquals("", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testBasic() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(142);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : ID INT {\n");
		grammarBuilder.append("document.getElementById('output').value += $text + '\\n';\n");
		grammarBuilder.append("};\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip;");
		String grammar = grammarBuilder.toString();
		String input ="abc 34";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "a", input, false);
		assertEquals("abc34\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testAorBStar_1() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(149);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : (ID|INT{\n");
		grammarBuilder.append("})* {\n");
		grammarBuilder.append("document.getElementById('output').value += $text + '\\n';\n");
		grammarBuilder.append("};\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "a", input, false);
		assertEquals("\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testMultipleEOFHandling() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(42);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("prog : ('x' | 'x' 'y') EOF EOF;");
		String grammar = grammarBuilder.toString();
		String input ="x";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "prog", input, false);
		assertEquals("", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testAorBStar_2() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(149);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : (ID|INT{\n");
		grammarBuilder.append("})* {\n");
		grammarBuilder.append("document.getElementById('output').value += $text + '\\n';\n");
		grammarBuilder.append("};\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("INT : '0'..'9'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="a 34 c";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "a", input, false);
		assertEquals("a34c\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testReferenceToATN_2() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(150);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : (ID|ATN)* ATN? {document.getElementById('output').value += $text + '\\n';} ;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("ATN : '0'..'9'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="a 34 c";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "a", input, false);
		assertEquals("a34c\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testAPlus() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(121);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : ID+ {\n");
		grammarBuilder.append("document.getElementById('output').value += $text + '\\n';\n");
		grammarBuilder.append("};\n");
		grammarBuilder.append("ID : 'a'..'z'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip;");
		String grammar = grammarBuilder.toString();
		String input ="a b c";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "a", input, false);
		assertEquals("abc\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testIfIfElseNonGreedyBinding1() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(231);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("start : statement+ ;\n");
		grammarBuilder.append("statement : 'x' | ifStatement;\n");
		grammarBuilder.append("ifStatement : 'if' 'y' statement ('else' statement)?? {\n");
		grammarBuilder.append("document.getElementById('output').value += $text + '\\n';\n");
		grammarBuilder.append("};\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> channel(HIDDEN);");
		String grammar = grammarBuilder.toString();
		String input ="if y if y x else x";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "start", input, false);
		assertEquals(
			"if y x\n" +
			"if y if y x else x\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testReferenceToATN_1() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(150);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : (ID|ATN)* ATN? {document.getElementById('output').value += $text + '\\n';} ;\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("ATN : '0'..'9'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip ;");
		String grammar = grammarBuilder.toString();
		String input ="";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "a", input, false);
		assertEquals("\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testIfIfElseNonGreedyBinding2() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(230);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("start : statement+ ;\n");
		grammarBuilder.append("statement : 'x' | ifStatement;\n");
		grammarBuilder.append("ifStatement : 'if' 'y' statement (|'else' statement) {\n");
		grammarBuilder.append("document.getElementById('output').value += $text + '\\n';\n");
		grammarBuilder.append("};\n");
		grammarBuilder.append("ID : 'a'..'z'+ ;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> channel(HIDDEN);");
		String grammar = grammarBuilder.toString();
		String input ="if y if y x else x";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "start", input, false);
		assertEquals(
			"if y x\n" +
			"if y if y x else x\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testAorAStar_1() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(126);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : (ID|ID)* {\n");
		grammarBuilder.append("document.getElementById('output').value += $text + '\\n';\n");
		grammarBuilder.append("};\n");
		grammarBuilder.append("ID : 'a'..'z'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip;");
		String grammar = grammarBuilder.toString();
		String input ="";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "a", input, false);
		assertEquals("\n", found);
		assertNull(this.stderrDuringParse);

	}
	/* this file and method are generated, any edit will be overwritten by the next generation */
	@Test
	public void testAorAStar_2() throws Exception {
		mkdir(tmpdir);
		StringBuilder grammarBuilder = new StringBuilder(126);
		grammarBuilder.append("grammar T;\n");
		grammarBuilder.append("a : (ID|ID)* {\n");
		grammarBuilder.append("document.getElementById('output').value += $text + '\\n';\n");
		grammarBuilder.append("};\n");
		grammarBuilder.append("ID : 'a'..'z'+;\n");
		grammarBuilder.append("WS : (' '|'\\n') -> skip;");
		String grammar = grammarBuilder.toString();
		String input ="a b c";
		String found = execParser("T.g4", grammar, "TParser", "TLexer",
		                          "TListener", "TVisitor",
		                          "a", input, false);
		assertEquals("abc\n", found);
		assertNull(this.stderrDuringParse);

	}

}
