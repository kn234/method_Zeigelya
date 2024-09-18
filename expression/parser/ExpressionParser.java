package expression.parser;

import expression.*;

import java.util.List;

import static expression.parser.Lexem.expr;
import static expression.parser.Lexem.parseLexem;

public class ExpressionParser implements TripleParser {
    //    expr: addsub()
//    addsub: multdiv()+-
//    multdiv: factor()*/(addsub)
//    factor: variables|const

    @Override
    public TripleExpression parse(String expression) {
        return expr(new Lexem.LexemBuffer(parseLexem(expression)));
    }

}
