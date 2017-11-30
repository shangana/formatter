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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.commons.lang.StringUtils;
import org.wisdom.tool.constant.FormatConst;
import org.wisdom.tool.util.FormatUtil;

/** 
* @ClassName: FormatTxtPanel 
* @Description: Formatter text panel 
* @Author: Dom Wang
* @Email: wisdomtool@outlook.com 
* @Date: 2017-07-22 PM 10:42:57 
* @version 1.0 
*/
public class FormatTxtPanel extends JPanel implements ActionListener
{
    private static final long serialVersionUID = 5120996065049850894L;

    private JTextArea txtAra = null;

    private JPopupMenu pm = null;

    private JMenuItem miFmt = null;

    private JMenuItem miCpy = null;

    private JMenuItem miPst = null;

    private JMenuItem miClr = null;

    private JMenuItem miCut = null;

    private MouseAdapter ma = new MouseAdapter()
    {
        private void popup(MouseEvent e)
        {
            txtAra.requestFocus();
            miPst.setEnabled(true);
            if (!txtAra.isEnabled() || StringUtils.isBlank(txtAra.getText()))
            {
                miFmt.setEnabled(false);
                miCut.setEnabled(false);
                miCpy.setEnabled(false);
                miClr.setEnabled(false);
            }
            else
            {
                miFmt.setEnabled(true);
                miCut.setEnabled(true);
                miCpy.setEnabled(true);
                miClr.setEnabled(true);
            }

            if (e.isPopupTrigger())
            {
                pm.show(e.getComponent(), e.getX(), e.getY());
            }
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
            this.popup(e);
        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            this.popup(e);
        }
    };

    public FormatTxtPanel()
    {
        this.init();
    }

    public JTextArea getTxtAra()
    {
        return txtAra;
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
        this.setLayout(new BorderLayout(FormatConst.BORDER_WIDTH, 0));

        txtAra = new JTextArea(FormatConst.AREA_ROWS, 1);
        txtAra.addMouseListener(ma);

        miFmt = new JMenuItem(FormatConst.FORMAT);
        miFmt.setName(FormatConst.FORMAT);
        miFmt.addActionListener(this);

        miCut = new JMenuItem(FormatConst.CUT);
        miCut.setName(FormatConst.CUT);
        miCut.addActionListener(this);

        miCpy = new JMenuItem(FormatConst.COPY);
        miCpy.setName(FormatConst.COPY);
        miCpy.addActionListener(this);

        miClr = new JMenuItem(FormatConst.CLEAR);
        miClr.setName(FormatConst.CLEAR);
        miClr.addActionListener(this);

        miPst = new JMenuItem(FormatConst.PASTE);
        miPst.setName(FormatConst.PASTE);
        miPst.addActionListener(this);
        
        pm = new JPopupMenu();
        pm.add(miFmt);
        pm.addSeparator();
        pm.add(miCut);
        pm.add(miCpy);
        pm.add(miPst);
        pm.addSeparator();
        pm.add(miClr);

        JPanel pnlCenter = new JPanel();
        pnlCenter.setLayout(new BorderLayout());
        JScrollPane sp = new JScrollPane(txtAra);
        pnlCenter.add(sp);

        this.add(pnlCenter, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e)
    {
        JMenuItem item = (JMenuItem) (e.getSource());
        if (FormatConst.FORMAT.equals(item.getName()))
        {
            String body = FormatUtil.format(txtAra.getText());
            txtAra.setText(body);
            txtAra.select(0, 0);
            return;
        }

        if (FormatConst.CUT.equals(item.getName()))
        {
            txtAra.selectAll();
            txtAra.cut();
            return;
        }

        if (FormatConst.COPY.equals(item.getName()))
        {
            txtAra.selectAll();
            txtAra.copy();
            txtAra.select(0, 0);
            return;
        }

        if (FormatConst.PASTE.equals(item.getName()))
        {
            txtAra.paste();
            txtAra.select(0, 0);
            return;
        }

        if (FormatConst.CLEAR.equals(item.getName()))
        {
            txtAra.setText(StringUtils.EMPTY);
            return;
        }
    }

}
