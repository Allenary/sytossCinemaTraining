package com.sytoss.training.cinema.domainservice;

import java.util.List;
import java.util.Map;

import com.sytoss.training.cinema.bom.Cinema;

public interface IReader {

  Map<String, Cinema> read(List<String> inputFileNames);
}
