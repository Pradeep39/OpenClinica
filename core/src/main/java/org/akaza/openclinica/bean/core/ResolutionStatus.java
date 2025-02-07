/*
 * OpenClinica is distributed under the
 * GNU Lesser General Public License (GNU LGPL).

 * For details see: http://www.openclinica.org/license
 * copyright 2003-2005 Akaza Research
 */
package org.akaza.openclinica.bean.core;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.akaza.openclinica.i18n.util.ResourceBundleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A type-safe enumeration class for resolution status of discrepancy notes
 *
 * @author Jun Xu
 *
 */

// Internationalized name and description in Term.getName and
// Term.getDescription()
public class ResolutionStatus extends Term {

    protected static final Logger logger = LoggerFactory.getLogger(ResolutionStatus.class.getName());

    public static final ResolutionStatus INVALID = new ResolutionStatus(0, "invalid", null, null);

    public static final ResolutionStatus OPEN = new ResolutionStatus(1, "New", null, "images/icon_Note.gif");

    public static final ResolutionStatus UPDATED = new ResolutionStatus(2, "Updated", null, "images/icon_flagYellow.gif");

    public static final ResolutionStatus RESOLVED = new ResolutionStatus(3, "Resolution_Proposed", null, "images/icon_flagGreen.gif");

    public static final ResolutionStatus CLOSED = new ResolutionStatus(4, "Closed", null, "images/icon_flagBlack.gif");

    public static final ResolutionStatus NOT_APPLICABLE = new ResolutionStatus(5, "Not_Applicable", null, "images/icon_flagWhite.gif");

    private String iconFilePath;

    public boolean isInvalid() {
        return this.getId() == ResolutionStatus.INVALID.getId();
    }

    public boolean isOpen() {
        return this.getId() == ResolutionStatus.OPEN.getId();
    }

    public boolean isClosed() {
        return this.getId() == ResolutionStatus.CLOSED.getId();
    }

    public boolean isUpdated() {
        return this.getId() == ResolutionStatus.UPDATED.getId();
    }

    public boolean isResolved() {
        return this.getId() == ResolutionStatus.RESOLVED.getId();
    }

    public boolean isNotApplicable() {
        return this.getId() == ResolutionStatus.NOT_APPLICABLE.getId();
    }

    private static final ResolutionStatus[] members = { OPEN, UPDATED, RESOLVED, CLOSED, NOT_APPLICABLE };

    public static ResolutionStatus[] getMembers() {
        return members;
    }

    public static final List<ResolutionStatus> list = Arrays.asList(members);

    private List privileges;

    private ResolutionStatus(int id, String name, Privilege[] myPrivs, String path) {
        super(id, name);
        this.iconFilePath = path;
    }

    private ResolutionStatus() {
    }

    public static boolean contains(int id) {
        return Term.contains(id, list);
    }

    public static ResolutionStatus get(int id) {
        return (ResolutionStatus) Term.get(id, list);
    }

    public static ResolutionStatus getByName(String name) {
        for (int i = 0; i < list.size(); i++) {
            ResolutionStatus temp = list.get(i);
            if (temp.getName().equals(name)) {
                return temp;
            }
        }
        return INVALID;
    }

    public boolean hasPrivilege(Privilege p) {
        Iterator it = privileges.iterator();

        while (it.hasNext()) {
            Privilege myPriv = (Privilege) it.next();
            if (myPriv.equals(p)) {
                return true;
            }
        }
        return false;
    }

    public String getIconFilePath() {
        return iconFilePath;
    }

    public void setIconFilePath(String iconFilePath) {
        this.iconFilePath = iconFilePath;
    }

    public static void main(String[] args) {
        ResourceBundleProvider.updateLocale(new Locale("en"));

        ResolutionStatus test = new ResolutionStatus(1, "New", null, null);
    }
}
