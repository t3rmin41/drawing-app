package com.simple.drawing.service;

import java.io.Serializable;
import java.util.List;
import com.simple.drawing.errorhandling.ErrorField;
import com.simple.drawing.errorhandling.WrongBeanFormatException;

public interface BeanValidator {

  List<ErrorField> validateBean(Serializable bean) throws WrongBeanFormatException;
  
}
