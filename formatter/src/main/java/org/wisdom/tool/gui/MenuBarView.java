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

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import org.apache.commons.lang.StringUtils;
import org.wisdom.tool.FormatMain;
import org.wisdom.tool.constant.FormatConst;
import org.wisdom.tool.util.FormatUtil;

/** 
 * @ClassName: MenuBarView 
 * @Description: Menu bar view
 * @author xawangyd 
 * @date 2017-07-22 PM 10:42:57 
 * @version 1.0 
 */
public class MenuBarView implements ActionListener
{
    private JMenuBar mb = null;

    private JFileChooser fc = null;

    private DonateDialog dd = null;

    private AboutDialog ad = null;

    public MenuBarView()
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
        JMenu mnFile = new JMenu(FormatConst.FILE);
        JMenu mnEdit = new JMenu(FormatConst.EDIT);
        JMenu mnHelp = new JMenu(FormatConst.HELP);

        // Menu of file
        JMenuItem miOpen = new JMenuItem(FormatConst.OPEN);
        JMenuItem miSave = new JMenuItem(FormatConst.SAVE);
        JMenuItem miExit = new JMenuItem(FormatConst.EXIT);

        miOpen.setName(FormatConst.OPEN);
        miOpen.addActionListener(this);
        miOpen.setToolTipText(FormatConst.OPEN + " " + FormatConst.FILE);

        miSave.setName(FormatConst.SAVE);
        miSave.addActionListener(this);
        miSave.setToolTipText(FormatConst.SAVE + " " + FormatConst.FILE);

        miExit.setName(FormatConst.EXIT);
        miExit.addActionListener(this);

        mnFile.add(miOpen);
        mnFile.addSeparator();
        mnFile.add(miSave);
        mnFile.addSeparator();
        mnFile.add(miExit);
        
        // Menu of edit
        JMenuItem miFmt = new JMenuItem(FormatConst.FORMAT);
        JMenuItem miCut = new JMenuItem(FormatConst.CUT);
        JMenuItem miCpy = new JMenuItem(FormatConst.COPY);
        JMenuItem miPst = new JMenuItem(FormatConst.PASTE);
        JMenuItem miClr = new JMenuItem(FormatConst.CLEAR);

        miFmt.setName(FormatConst.FORMAT);
        miFmt.addActionListener(this);

        miCut.setName(FormatConst.CUT);
        miCut.addActionListener(this);

        miCpy.setName(FormatConst.COPY);
        miCpy.addActionListener(this);

        miPst.setName(FormatConst.PASTE);
        miPst.addActionListener(this);

        miClr.setName(FormatConst.CLEAR);
        miClr.addActionListener(this);

        mnEdit.add(miFmt);
        mnEdit.addSeparator();
        mnEdit.add(miCut);
        mnEdit.add(miCpy);
        mnEdit.add(miPst);
        mnEdit.addSeparator();
        mnEdit.add(miClr);

        // Menu of help
        JMenuItem miIssue = new JMenuItem(FormatConst.REPORT_ISSUE);
        JMenuItem miDonate = new JMenuItem(FormatConst.DONATE);
        JMenuItem miAbout = new JMenuItem(FormatConst.ABOUT_TOOL);

        miIssue.setName(FormatConst.REPORT_ISSUE);
        miIssue.addActionListener(this);

        miDonate.setName(FormatConst.DONATE);
        miDonate.addActionListener(this);

        miAbout.setName(FormatConst.ABOUT_TOOL);
        miAbout.addActionListener(this);

        mnHelp.add(miIssue);
        mnHelp.addSeparator();
        mnHelp.add(miDonate);
        mnHelp.addSeparator();
        mnHelp.add(miAbout);

        ad = new AboutDialog();
        dd = new DonateDialog();

        // MenuBar
        mb = new JMenuBar();
        mb.setBorder(BorderFactory.createEtchedBorder());
        mb.add(mnFile);
        mb.add(mnEdit);
        mb.add(mnHelp);
        fc = new JFileChooser();
    }

    public JMenuBar getJMenuBar()
    {
        return mb;
    }

    /**
    * 
    * @Title: filePerformed 
    * @Description: File Menu Item Performed
    * @param @param item
    * @return void 
    * @throws
     */
    private void filePerformed(JMenuItem item)
    {
        JTextArea ta = FormatView.getView().getTxtPanel().getTxtAra();
        if (FormatConst.OPEN.equals(item.getName()))
        {
            String content = FormatUtil.openFile(FormatView.getView(), fc);
            ta.setText(content);
            return;
        }

        if (FormatConst.SAVE.equals(item.getName()))
        {
            FormatUtil.saveFile(FormatView.getView(), fc);
            return;
        }

        if (FormatConst.EXIT.equals(item.getName()))
        {
            FormatMain.closeView();
            return;
        }
    }
    
    /**
    * 
    * @Title: editPerformed 
    * @Description: Edit Menu Item Performed 
    * @param @param item 
    * @return void 
    * @throws
     */
    private void editPerformed(JMenuItem item)
    {
        JTextArea ta = FormatView.getView().getTxtPanel().getTxtAra();

        if (FormatConst.FORMAT.equals(item.getName()))
        {
            String body = FormatUtil.format(ta.getText());
            ta.setText(body);
            ta.select(0, 0);
            return;
        }

        if (FormatConst.CUT.equals(item.getName()))
        {
            ta.selectAll();
            ta.cut();
            return;
        }

        if (FormatConst.COPY.equals(item.getName()))
        {
            StringSelection ss = null;
            String seltxt = ta.getSelectedText();
            if (StringUtils.isNotBlank(seltxt))
            {
                ss = new StringSelection(seltxt);
            }
            else
            {
                ss = new StringSelection(ta.getText());
            }

            Toolkit.getDefaultToolkit()
                   .getSystemClipboard()
                   .setContents(ss, null);
            return;
        }

        if (FormatConst.PASTE.equals(item.getName()))
        {
            ta.paste();
            ta.select(0, 0);
            return;
        }

        if (FormatConst.CLEAR.equals(item.getName()))
        {
            ta.setText(StringUtils.EMPTY);
            return;
        }

    }
    
    /**
    * 
    * @Title: helpPerformed 
    * @Description: Help Menu Item Performed 
    * @param @param item
    * @return void
    * @throws
     */
    private void helpPerformed(JMenuItem item)
    {
        if (FormatConst.ABOUT_TOOL.equals(item.getText()))
        {
            ad.setVisible(true);
            FormatUtil.setLocation(ad);
            return;
        }

        if (FormatConst.DONATE.equals(item.getText()))
        {
            dd.setVisible(true);
            FormatUtil.setLocation(dd);
            return;
        }
 
        if (FormatConst.REPORT_ISSUE.equals(item.getText()))
        {
            try
            {
                Desktop.getDesktop().browse(new URI(FormatConst.URL_ISSUE));
            }
            catch(Exception e)
            {
                FormatView.getView().getStat().setText("Failed to open report issue site: " + e.getMessage());
            }
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        JMenuItem item = (JMenuItem) (e.getSource());
        this.filePerformed(item);
        this.editPerformed(item);
        this.helpPerformed(item);
    }

}
