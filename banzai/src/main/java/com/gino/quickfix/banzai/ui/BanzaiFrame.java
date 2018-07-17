/*******************************************************************************
 * Copyright (c) quickfixengine.org  All rights reserved.
 *
 * This file is part of the QuickFIX FIX Engine
 *
 * This file may be distributed under the terms of the quickfixengine.org
 * license as defined by quickfixengine.org and appearing in the file
 * LICENSE included in the packaging of this file.
 *
 * This file is provided AS IS with NO WARRANTY OF ANY KIND, INCLUDING
 * THE WARRANTY OF DESIGN, MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE.
 *
 * See http://www.quickfixengine.org/LICENSE for licensing information.
 *
 * Contact ask@quickfixengine.org if any conditions of this licensing
 * are not clear to you.
 ******************************************************************************/

package com.gino.quickfix.banzai.ui;


import com.gino.quickfix.banzai.Banzai;
import com.gino.quickfix.banzai.BanzaiApplication;
import com.gino.quickfix.banzai.ExecutionTableModel;
import com.gino.quickfix.banzai.OrderTableModel;

import javax.swing.*;
import java.awt.*;

/**
 * Main application window
 */
public class BanzaiFrame extends JFrame {

    public BanzaiFrame(OrderTableModel orderTableModel, ExecutionTableModel executionTableModel,
                       final BanzaiApplication application) {
        super();
        setTitle("Banzai!");
        setSize(600, 400);

        if (System.getProperties().containsKey("openfix")) {
            createMenuBar(application);
        }
        getContentPane().add(new BanzaiPanel(orderTableModel, executionTableModel, application),
                BorderLayout.CENTER);
        setVisible(true);
    }

    private void createMenuBar(final BanzaiApplication application) {
        JMenuBar menubar = new JMenuBar();

        JMenu sessionMenu = new JMenu("Session");
        menubar.add(sessionMenu);

        JMenuItem logonItem = new JMenuItem("Logon");
        logonItem.addActionListener(e -> Banzai.get().logon());
        sessionMenu.add(logonItem);

        JMenuItem logoffItem = new JMenuItem("Logoff");
        logoffItem.addActionListener(e -> Banzai.get().logout());
        sessionMenu.add(logoffItem);

        JMenu appMenu = new JMenu("Application");
        menubar.add(appMenu);

        JMenuItem appAvailableItem = new JCheckBoxMenuItem("Available");
        appAvailableItem.setSelected(application.isAvailable());
        appAvailableItem.addActionListener(e -> application.setAvailable(((JCheckBoxMenuItem) e.getSource()).isSelected()));
        appMenu.add(appAvailableItem);

        JMenuItem sendMissingFieldRejectItem = new JCheckBoxMenuItem("Send Missing Field Reject");
        sendMissingFieldRejectItem.setSelected(application.isMissingField());
        sendMissingFieldRejectItem.addActionListener(e -> application.setMissingField(((JCheckBoxMenuItem) e.getSource()).isSelected()));
        appMenu.add(sendMissingFieldRejectItem);

        setJMenuBar(menubar);
    }
}
