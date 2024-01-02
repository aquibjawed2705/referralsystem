package com.suggest.referal.ReferalSystem.util;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class ProductUtils {
  public static Set<String> getFilteredData(String s) {
    Set<String> productIds = new HashSet<>();
    String patternString = "data-id=\"(.*?)\"";
    /*try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
        String line;
        while ((line = reader.readLine()) != null) {
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String extractedValue = matcher.group(1);
                System.out.println("Extracted Value: " + extractedValue);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }*/
    Pattern pattern = Pattern.compile(patternString);
    Matcher matcher = pattern.matcher(s);
    while (matcher.find()) {
      String extractedValue = matcher.group(1);
      log.info("Extracted Value: " + extractedValue);
      productIds.add(extractedValue);
    }
    return productIds;
  }

  public static Set<String> getFilteredDataFromFetch(String s) {
    Set<String> productIds = new HashSet<>();
    String patternString = "\"productId\"\\s*:\\s*\"([^\"]+)\"";
    Pattern pattern = Pattern.compile(patternString);
    Matcher matcher = pattern.matcher(s);
    while (matcher.find()) {
      String extractedValue = matcher.group(1);
      log.info("Extracted Value: " + extractedValue);
      productIds.add(extractedValue);
    }
    return productIds;
  }
}
