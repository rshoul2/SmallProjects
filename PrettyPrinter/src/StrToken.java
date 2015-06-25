

class StrToken extends Token {
  private String strVal;

  public StrToken(String s) {
    super(TokenType.STRING);
    strVal = s;
  }

    @Override
  String getStrVal() {
    return strVal;
  }
}
