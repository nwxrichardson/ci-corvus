/**
 */
package psl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import psl.Deliverable;
import psl.Effort;
import psl.PslPackage;
import psl.Task;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Task</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link psl.impl.TaskImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link psl.impl.TaskImpl#getStart <em>Start</em>}</li>
 *   <li>{@link psl.impl.TaskImpl#getDuration <em>Duration</em>}</li>
 *   <li>{@link psl.impl.TaskImpl#getEffort <em>Effort</em>}</li>
 *   <li>{@link psl.impl.TaskImpl#getDependencies <em>Dependencies</em>}</li>
 *   <li>{@link psl.impl.TaskImpl#getDeliverables <em>Deliverables</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TaskImpl extends MinimalEObjectImpl.Container implements Task {
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
	 * The default value of the '{@link #getStart() <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStart()
	 * @generated
	 * @ordered
	 */
	protected static final int START_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getStart() <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStart()
	 * @generated
	 * @ordered
	 */
	protected int start = START_EDEFAULT;

	/**
	 * The default value of the '{@link #getDuration() <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuration()
	 * @generated
	 * @ordered
	 */
	protected static final int DURATION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getDuration() <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuration()
	 * @generated
	 * @ordered
	 */
	protected int duration = DURATION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getEffort() <em>Effort</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEffort()
	 * @generated
	 * @ordered
	 */
	protected EList<Effort> effort;

	/**
	 * The cached value of the '{@link #getDependencies() <em>Dependencies</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDependencies()
	 * @generated
	 * @ordered
	 */
	protected EList<Task> dependencies;

	/**
	 * The cached value of the '{@link #getDeliverables() <em>Deliverables</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeliverables()
	 * @generated
	 * @ordered
	 */
	protected EList<Deliverable> deliverables;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TaskImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PslPackage.Literals.TASK;
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
			eNotify(new ENotificationImpl(this, Notification.SET, PslPackage.TASK__TITLE, oldTitle, title));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getStart() {
		return start;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStart(int newStart) {
		int oldStart = start;
		start = newStart;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PslPackage.TASK__START, oldStart, start));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDuration(int newDuration) {
		int oldDuration = duration;
		duration = newDuration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PslPackage.TASK__DURATION, oldDuration, duration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Effort> getEffort() {
		if (effort == null) {
			effort = new EObjectContainmentEList<Effort>(Effort.class, this, PslPackage.TASK__EFFORT);
		}
		return effort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Task> getDependencies() {
		if (dependencies == null) {
			dependencies = new EObjectResolvingEList<Task>(Task.class, this, PslPackage.TASK__DEPENDENCIES);
		}
		return dependencies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Deliverable> getDeliverables() {
		if (deliverables == null) {
			deliverables = new EObjectContainmentEList<Deliverable>(Deliverable.class, this, PslPackage.TASK__DELIVERABLES);
		}
		return deliverables;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PslPackage.TASK__EFFORT:
				return ((InternalEList<?>)getEffort()).basicRemove(otherEnd, msgs);
			case PslPackage.TASK__DELIVERABLES:
				return ((InternalEList<?>)getDeliverables()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PslPackage.TASK__TITLE:
				return getTitle();
			case PslPackage.TASK__START:
				return getStart();
			case PslPackage.TASK__DURATION:
				return getDuration();
			case PslPackage.TASK__EFFORT:
				return getEffort();
			case PslPackage.TASK__DEPENDENCIES:
				return getDependencies();
			case PslPackage.TASK__DELIVERABLES:
				return getDeliverables();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PslPackage.TASK__TITLE:
				setTitle((String)newValue);
				return;
			case PslPackage.TASK__START:
				setStart((Integer)newValue);
				return;
			case PslPackage.TASK__DURATION:
				setDuration((Integer)newValue);
				return;
			case PslPackage.TASK__EFFORT:
				getEffort().clear();
				getEffort().addAll((Collection<? extends Effort>)newValue);
				return;
			case PslPackage.TASK__DEPENDENCIES:
				getDependencies().clear();
				getDependencies().addAll((Collection<? extends Task>)newValue);
				return;
			case PslPackage.TASK__DELIVERABLES:
				getDeliverables().clear();
				getDeliverables().addAll((Collection<? extends Deliverable>)newValue);
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
			case PslPackage.TASK__TITLE:
				setTitle(TITLE_EDEFAULT);
				return;
			case PslPackage.TASK__START:
				setStart(START_EDEFAULT);
				return;
			case PslPackage.TASK__DURATION:
				setDuration(DURATION_EDEFAULT);
				return;
			case PslPackage.TASK__EFFORT:
				getEffort().clear();
				return;
			case PslPackage.TASK__DEPENDENCIES:
				getDependencies().clear();
				return;
			case PslPackage.TASK__DELIVERABLES:
				getDeliverables().clear();
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
			case PslPackage.TASK__TITLE:
				return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
			case PslPackage.TASK__START:
				return start != START_EDEFAULT;
			case PslPackage.TASK__DURATION:
				return duration != DURATION_EDEFAULT;
			case PslPackage.TASK__EFFORT:
				return effort != null && !effort.isEmpty();
			case PslPackage.TASK__DEPENDENCIES:
				return dependencies != null && !dependencies.isEmpty();
			case PslPackage.TASK__DELIVERABLES:
				return deliverables != null && !deliverables.isEmpty();
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
		result.append(", start: ");
		result.append(start);
		result.append(", duration: ");
		result.append(duration);
		result.append(')');
		return result.toString();
	}

} //TaskImpl
