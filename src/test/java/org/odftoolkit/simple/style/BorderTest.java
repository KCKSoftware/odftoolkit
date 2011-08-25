/************************************************************************
 *
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER
 *
 * Copyright 2010 IBM. All rights reserved.
 *
 * Use is subject to license terms.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at http://www.apache.org/licenses/LICENSE-2.0. You can also
 * obtain a copy of the License at http://odftoolkit.org/docs/license.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ************************************************************************/
package org.odftoolkit.simple.style;

import junit.framework.Assert;

import org.junit.Test;
import org.odftoolkit.odfdom.type.Color;
import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.style.StyleTypeDefinitions.SimpleCellBordersType;
import org.odftoolkit.simple.style.StyleTypeDefinitions.SupportedLinearMeasure;
import org.odftoolkit.simple.table.Cell;
import org.odftoolkit.simple.table.Table;
import org.odftoolkit.simple.utils.ResourceUtilities;

public class BorderTest {

	static final String filename = "testGetCellAt.ods";
	
	@Test
	public void testGetSetBorder() {
		Border borderbase1 = new Border(new Color("#ff3333"), 5, SupportedLinearMeasure.PT);
		Border borderbase2 = new Border(new Color("#0000ff"), 0.0154, 0.0008, 0.0008, SupportedLinearMeasure.IN);
		Border borderbase3 = new Border(new Color("#ff3333"), 0.0362, 0.0008, 0.0008, SupportedLinearMeasure.IN);
		Border borderbase4 = new Border(new Color("#00ccff"), 0.0701, 0.0008, 0.0346, SupportedLinearMeasure.IN);
		try {
			SpreadsheetDocument doc = SpreadsheetDocument.loadDocument(ResourceUtilities
					.getTestResourceAsStream(filename));
			Table table = doc.getTableByName("A");
			Cell cell1 = table.getCellByPosition("A8");
			Border border1 = cell1.getStyleHandler().getBorder(SimpleCellBordersType.BOTTOM);
			Border border11 = cell1.getStyleHandler().getBorder(SimpleCellBordersType.LEFT);
			Assert.assertEquals(borderbase1, border1);
			Assert.assertEquals(borderbase1, border11);

			Cell cell2 = table.getCellByPosition("A10");
			Border border2 = cell2.getStyleHandler().getBorder(SimpleCellBordersType.TOP);
			Assert.assertEquals(borderbase2, border2);
			Border border3 = cell2.getStyleHandler().getBorder(SimpleCellBordersType.DIAGONALBLTR);
			Assert.assertEquals(borderbase3, border3);

			Cell cell3 = table.getCellByPosition("A12");
			Border border4 = cell3.getStyleHandler().getBorder(SimpleCellBordersType.LEFT);
			Border border41 = cell3.getStyleHandler().getBorder(SimpleCellBordersType.RIGHT);
			Assert.assertEquals(borderbase4, border4);
			Assert.assertEquals(borderbase4, border41);

			Cell cell4 = table.getCellByPosition("B8");
			cell4.setBorders(SimpleCellBordersType.ALL_FOUR, borderbase1);
			Border border5 = cell4.getStyleHandler().getBorder(SimpleCellBordersType.BOTTOM);
			Border border51 = cell4.getStyleHandler().getBorder(SimpleCellBordersType.LEFT);
			Assert.assertEquals(borderbase1, border5);
			Assert.assertEquals(borderbase1, border51);

			Cell cell5 = table.getCellByPosition("B10");
			cell5.setBorders(SimpleCellBordersType.TOP, borderbase2);
			cell5.setBorders(SimpleCellBordersType.DIAGONALBLTR, borderbase3);
			Assert.assertEquals(borderbase2, cell5.getStyleHandler().getBorder(SimpleCellBordersType.TOP));
			Assert.assertEquals(borderbase3, cell5.getStyleHandler().getBorder(SimpleCellBordersType.DIAGONALBLTR));

			Cell cell6 = table.getCellByPosition("B12");
			cell6.setBorders(SimpleCellBordersType.LEFT_RIGHT, borderbase4);
			Assert.assertEquals(borderbase4, cell6.getStyleHandler().getBorder(SimpleCellBordersType.LEFT));
			Assert.assertEquals(borderbase4, cell6.getStyleHandler().getBorder(SimpleCellBordersType.RIGHT));

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

	}
}
