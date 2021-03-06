package org.swrlapi.ui.dialog;

import java.util.ArrayList;
import java.util.List;

class SWRLRuleEditorAutoCompleteState
{
  private final int textPosition;
  private final String prefix;
  private final List<String> expansions;
  private int expansionIndex;

  public SWRLRuleEditorAutoCompleteState(int textPosition, String prefix, List<String> expansions)
  {
    this.textPosition = textPosition;
    this.prefix = prefix;
    this.expansions = new ArrayList<>(expansions);
    this.expansionIndex = 0;
  }

  public int getTextPosition()
  {
    return this.textPosition;
  }

  public String getPrefix()
  {
    return this.prefix;
  }

  public String getCurrentExpansion()
  {
    if (!this.expansions.isEmpty()) {
      return this.expansions.get(this.expansionIndex);
    } else
      return "";
  }

  public String getNextExpansion()
  {
    if (!this.expansions.isEmpty()) {
      this.expansionIndex++;

      if (this.expansionIndex == this.expansions.size())
        this.expansionIndex = 0;

      return this.expansions.get(this.expansionIndex);
    } else
      return "";
  }
}
