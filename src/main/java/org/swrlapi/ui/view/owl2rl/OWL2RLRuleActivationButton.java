package org.swrlapi.ui.view.owl2rl;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import org.swrlapi.owl2rl.OWL2RLNames;
import org.swrlapi.ui.model.OWL2RLModel;

public class OWL2RLRuleActivationButton extends JCheckBox implements ActionListener
{
  private static final long serialVersionUID = 1L;

  private static final int TOOLTIP_PREFERRED_WIDTH = 160;
  private static final int TOOLTIP_PREFERRED_HEIGHT = 30;

  private final OWL2RLModel owl2RLModel;
  private final OWL2RLNames.OWL2RLRule rule;

  public OWL2RLRuleActivationButton(OWL2RLModel owl2RLModel, OWL2RLNames.OWL2RLRule rule)
  {
    super(rule.toString());

    this.owl2RLModel = owl2RLModel;
    this.rule = rule;

    initialize();
  }

  public void update()
  {
    switch (getOWL2RLModel().getOWL2RLEngine().getRuleStatus(this.rule)) {
    case Unsupported:
      setSelected(false);
      setEnabled(false);
      break;
    case PermanentlyOn:
      setSelected(true);
      setEnabled(false);
      break;
    case Switchable:
      setSelected(getOWL2RLModel().getOWL2RLEngine().isRuleEnabled(this.rule));
      setEnabled(true);
      break;
    }
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    if (isSelected())
      getOWL2RLModel().getOWL2RLEngine().enableRules(this.rule);
    else
      getOWL2RLModel().getOWL2RLEngine().disableRules(this.rule);

    getOWL2RLModel().updateView();
  }

  private void initialize()
  {
    setPreferredSize(new Dimension(TOOLTIP_PREFERRED_WIDTH, TOOLTIP_PREFERRED_HEIGHT));
    setToolTipText("Click to enable or disable OWL 2 RL rule " + this.rule.toString() + ".");
    addActionListener(this);
    update();
  }

  private OWL2RLModel getOWL2RLModel()
  {
    return this.owl2RLModel;
  }
}
