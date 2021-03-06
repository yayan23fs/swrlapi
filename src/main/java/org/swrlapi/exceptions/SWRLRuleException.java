package org.swrlapi.exceptions;

public class SWRLRuleException extends SWRLBuiltInException
{
  private static final long serialVersionUID = 1L;

  public SWRLRuleException(String message)
  {
    super(message);
  }

  public SWRLRuleException(String message, Throwable cause)
  {
    super(message, cause);
  }
}
