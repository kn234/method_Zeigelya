package expression.parser;

import expression.*;

import java.util.ArrayList;
import java.util.List;

public class Lexem {
    LexemesType type;
    String in;

    public Lexem(LexemesType type, String in) {
        this.type = type;
        this.in = in;
    }

    public Lexem(LexemesType type, char in) {
        this.type = type;
        this.in = Character.toString(in);
    }

    public String getIn() {
        return in;
    }

    @Override
    public String toString() {
        return type + " " + in;
    }

    public static List<Lexem> parseLexem(String expr) {
        List<Lexem> result = new ArrayList<>();
        int pos = 0;
        while (pos < expr.length()) {
            char currChar = expr.charAt(pos);
            switch (expr.charAt(pos)) {
                case '(':
                    result.add(new Lexem(LexemesType.LEFTBRACKET, currChar));
                    pos++;
                    continue;
                case ')':
                    result.add(new Lexem(LexemesType.RIGHTBRACKET, currChar));
                    pos++;
                    continue;
                case '+':
                    result.add(new Lexem(LexemesType.OPADD, currChar));
                    pos++;
                    continue;
                case '-':
                    if (result.isEmpty() || result.get(result.size() - 1).type == LexemesType.LEFTBRACKET ||
                            result.get(result.size() - 1).type == LexemesType.OPADD || result.get(result.size() - 1).type == LexemesType.OPSUB ||
                            result.get(result.size() - 1).type == LexemesType.OPDIV || result.get(result.size() - 1).type == LexemesType.OPMUL) {
                        result.add(new Lexem(LexemesType.NEGATE, currChar));
                    } else {
                        result.add(new Lexem(LexemesType.OPSUB, currChar));
                    }
                    pos++;
                    continue;
                case '*':
                    result.add(new Lexem(LexemesType.OPMUL, currChar));
                    pos++;
                    continue;
                case '/':
                    result.add(new Lexem(LexemesType.OPDIV, currChar));
                    pos++;
                    continue;
                default:
                    if (expr.charAt(pos) <= '9' && expr.charAt(pos) >= '0') {
                        StringBuilder sb = new StringBuilder();
                        do {
                            sb.append(expr.charAt(pos));
                            pos++;
                            if (pos >= expr.length()) {
                                break;
                            }
                        } while (Character.isDigit(expr.charAt(pos)));
                        if (!result.isEmpty() && result.get(result.size() - 1).type == LexemesType.NEGATE && sb.toString().equals("2147483648")) {
                            result.remove(result.size() - 1);
                            result.add(new Lexem(LexemesType.CONST, "-" + sb));
                        } else {
                            result.add(new Lexem(LexemesType.CONST, sb.toString()));
                        }
                    } else if (expr.charAt(pos) == 'x' || expr.charAt(pos) == 'z' || expr.charAt(pos) == 'y') {
                        result.add(new Lexem(LexemesType.VAR, expr.charAt(pos)));
                        pos++;
                    } else {
                        if (Character.isWhitespace(expr.charAt(pos))) {
                            pos++;
                        }
                    }

            }

        }
        result.add(new Lexem(LexemesType.EOF, ""));
        return result;
    }

    public static class LexemBuffer {
        private int pos;
        public List<Lexem> lexemes;

        public LexemBuffer(List<Lexem> lexemes) {
            this.lexemes = lexemes;
        }

        public Lexem next() {
            return pos < lexemes.size() - 1 ? lexemes.get(pos++) : lexemes.get(pos);
        }

        public void back() {
            pos--;
        }

        public float getPos() {
            return pos;
        }

    }

    public static Expressions expr(LexemBuffer lexemes) {
        Lexem lexeme = lexemes.next();
        if (lexeme.type == LexemesType.NEGATE) {
            lexemes.back();
            return addsub(lexemes);
        } else {
            lexemes.back();
            return addsub(lexemes);
        }
    }

    public static Expressions addsub(LexemBuffer lexemes) {
        Expressions result = multdiv(lexemes);
        while (true) {
            Lexem lexeme = lexemes.next();
            switch (lexeme.type) {
                case OPADD -> result = new Add(result, multdiv(lexemes));
                case OPSUB -> result = new Subtract(result, multdiv(lexemes));
                default -> {
                    lexemes.back();
                    return result;
                }
            }
        }
    }

    public static Expressions multdiv(LexemBuffer lexemes) {
        Expressions result = factor(lexemes);
        while (true) {
            Lexem lexeme = lexemes.next();
            switch (lexeme.type) {
                case OPMUL -> result = new Multiply(result, factor(lexemes));
                case OPDIV -> result = new Divide(result, factor(lexemes));
                default -> {
                    lexemes.back();
                    return result;
                }
            }
        }
    }

    public static Expressions factor(LexemBuffer lexemes) {
        Lexem lexeme = lexemes.next();
        switch (lexeme.type) {
            case CONST:
                return new Const(Integer.parseInt((lexeme.getIn())));
            case VAR:
                return new Variable(lexeme.getIn());
            case NEGATE:
                return new Negate(factor(lexemes));
            case LEFTBRACKET:
                Expressions result = expr(lexemes);
                lexeme = lexemes.next();
                return result;

            default:
                throw new RuntimeException("error");
        }
    }

    public static void main(String[] args) {

        System.out.println(expr(new LexemBuffer(parseLexem("-(0)"))).toMiniString());
    }
}
