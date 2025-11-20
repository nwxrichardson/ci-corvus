/**
 */
package psl.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import psl.Deliverable;
import psl.Person;
import psl.PslPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Deliverable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link psl.impl.DeliverableImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link psl.impl.DeliverableImpl#getDue <em>Due</em>}</li>
 *   <li>{@link psl.impl.DeliverableImpl#getLead <em>Lead</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DeliverableImpl extends MinimalEObjectImpl.Container implements Deliverable {
	/**
	 * The default value of the '{@link #getTitle() <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected static final String TITLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTitle() <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected String title = TITLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDue() <em>Due</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDue()
	 * @generated
	 * @ordered
	 */
	protected static final int DUE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getDue() <em>Due</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDue()
	 * @generated
	 * @ordered
	 */
	protected int due = DUE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLead() <em>Lead</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLead()
	 * @generated
	 * @ordered
	 */
	protected Person lead;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeliverableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PslPackage.Literals.DELIVERABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTitle(String newTitle) {
		String oldTitle = title;
		title = newTitle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PslPackage.DELIVERABLE__TITLE, oldTitle, title));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getDue() {
		return due;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDue(int newDue) {
		int oldDue = due;
		due = newDue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PslPackage.DELIVERABLE__DUE, oldDue, due));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Person getLead() {
		if (lead != null && lead.eIsProxy()) {
			InternalEObject oldLead = (InternalEObject)lead;
			lead = (Person)eResolveProxy(oldLead);
			if (lead != oldLead) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PslPackage.DELIVERABLE__LEAD, oldLead, lead));
			}
		}
		return lead;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Person basicGetLead() {
		return lead;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLead(Person newLead) {
		Person oldLead = lead;
		lead = newLead;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PslPackage.DELIVERABLE__LEAD, oldLead, lead));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PslPackage.DELIVERABLE__TITLE:
				return getTitle();
			case PslPackage.DELIVERABLE__DUE:
				return getDue();
			case PslPackage.DELIVERABLE__LEAD:
				if (resolve) return getLead();
				return basicGetLead();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PslPackage.DELIVERABLE__TITLE:
				setTitle((String)newValue);
				return;
			case PslPackage.DELIVERABLE__DUE:
				setDue((Integer)newValue);
				return;
			case PslPackage.DELIVERABLE__LEAD:
				setLead((Person)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case PslPackage.DELIVERABLE__TITLE:
				setTitle(TITLE_EDEFAULT);
				return;
			case PslPackage.DELIVERABLE__DUE:
				setDue(DUE_EDEFAULT);
				return;
			case PslPackage.DELIVERABLE__LEAD:
				setLead((Person)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case PslPackage.DELIVERABLE__TITLE:
				return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
			case PslPackage.DELIVERABLE__DUE:
				return due != DUE_EDEFAULT;
			case PslPackage.DELIVERABLE__LEAD:
				return lead != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (title: ");
		result.append(title);
		result.append(", due: ");
		result.append(due);
		result.append(')');
		return result.toString();
	}

} //DeliverableImpl
