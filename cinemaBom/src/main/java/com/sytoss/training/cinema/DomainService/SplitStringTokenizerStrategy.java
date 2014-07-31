package com.sytoss.training.cinema.DomainService;

import java.util.StringTokenizer;

public class SplitStringTokenizerStrategy implements ISplitStategy {

  public String[] splitByCommas(String rowToParse) {
    StringTokenizer tokenizer = new StringTokenizer(rowToParse, ",");
    String[] tokens = new String[tokenizer.countTokens()];
    for (int i = 0; i < tokens.length; i++ ) {
      tokens[i] = tokenizer.nextToken();
    }

    return tokens;
  }

}
