package org.swrlapi.ui.view.owl2rl;

import org.swrlapi.owl2rl.OWL2RLEngine;
import org.swrlapi.owl2rl.OWL2RLNames;
import org.swrlapi.ui.model.OWL2RLModel;
import org.swrlapi.ui.view.SWRLAPIView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class OWL2RLRuleTablesView extends JTabbedPane implements SWRLAPIView
{
  private static final long serialVersionUID = 1L;

  private final OWL2RLModel owl2RLModel;
  private final OWL2RLControlView owl2RLControlView;
  private final List<OWL2RLRuleTableView> owl2RLTableViews;

  public OWL2RLRuleTablesView(OWL2RLEngine owl2RLEngine)
  {
    this.owl2RLModel = new OWL2RLModel(this, owl2RLEngine);
    this.owl2RLTableViews = new ArrayList<>();
    this.owl2RLControlView = new OWL2RLControlView(this.owl2RLModel);

    initialize();
  }

  @Override
  public void update()
  {
    for (OWL2RLRuleTableView owl2RLTableView : this.owl2RLTableViews)
      owl2RLTableView.update();

    this.owl2RLControlView.update();
  }

  private void initialize()
  {
    addTab("OWL2RL Control", this.owl2RLControlView);

    for (OWL2RLNames.OWL2RLRuleTable ruleTable : getOWL2RLModel().getOWL2RLEngine().getRuleTables()) {
      OWL2RLRuleTableView owl2RLTableView = new OWL2RLRuleTableView(getOWL2RLModel(), ruleTable);
      this.owl2RLTableViews.add(owl2RLTableView);
      addTab(ruleTable.toString(), owl2RLTableView);
    }
  }

  private OWL2RLModel getOWL2RLModel()
  {
    return this.owl2RLModel;
  }
}
