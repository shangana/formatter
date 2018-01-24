/* 
 * Copyright 2016-2017 WisdomTool.org. All Rights Reserved.
 * 
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
   
 *  http://www.wisdomtool.org/licenses
 * 
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.wisdom.tool.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import org.wisdom.tool.constant.FormatConst;
import org.wisdom.tool.util.FormatUtil;

/** 
* @ClassName: DonateDialog 
* @Description: Donate Dialog
* @Author: Dom Wang
* @Email: witpool@outlook.com 
* @Date: 2017-07-22 PM 10:42:57 
* @version 1.0 
*/
public class DonateDialog extends JDialog implements ActionListener
{
    private static final long serialVersionUID = -2821579370172523357L;

    public DonateDialog()
    {
        this.init();
    }
   
    /**
    * 
    * @Title: init 
    * @Description: Component Initialization 
    * @param
    * @return void 
    * @throws
     */
    private void init()
    {
        this.setTitle(FormatConst.DONATE_BY_PAY);
        this.setLayout(new BorderLayout(FormatConst.BORDER_WIDTH, FormatConst.BORDER_WIDTH));

        JPanel pnlDialog = new JPanel();
        pnlDialog.setLayout(new BorderLayout());

        JLabel lblDonate = new JLabel();
        lblDonate.setIcon(FormatUtil.getIcon(FormatConst.DONATE_ICON));
        lblDonate.setToolTipText(FormatConst.DONATE_BY_PAY);

        JPanel pnlNorth = new JPanel();
        pnlNorth.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlNorth.add(lblDonate);
        pnlDialog.add(pnlNorth, BorderLayout.NORTH);

        JPanel pnlCenter = new JPanel();
        pnlCenter.setLayout(new GridLayout(1, 1));
        JTextPane tp = new JTextPane();
        tp.setEditable(false);
        tp.setContentType("text/html");
        tp.setText(FormatUtil.contents(FormatConst.DONATION));
        pnlCenter.add(new JScrollPane(tp));
        pnlDialog.add(pnlCenter, BorderLayout.CENTER);

        JPanel pnlSouth = new JPanel();
        pnlSouth.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton btnOK = new JButton(FormatConst.OK);
        btnOK.addActionListener(this);

        getRootPane().setDefaultButton(btnOK);
        pnlSouth.add(btnOK);
        pnlDialog.add(pnlSouth, BorderLayout.SOUTH);

        this.setContentPane(pnlDialog);
        pack();
        btnOK.requestFocus();
        this.setIconImage(FormatUtil.getImage(FormatConst.LOGO));
    }

    public void actionPerformed(ActionEvent arg0)
    {
        this.setVisible(false);
    }

}
