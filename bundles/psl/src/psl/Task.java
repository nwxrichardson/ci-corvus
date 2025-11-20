/**
 */
package psl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link psl.Task#getTitle <em>Title</em>}</li>
 *   <li>{@link psl.Task#getStart <em>Start</em>}</li>
 *   <li>{@link psl.Task#getDuration <em>Duration</em>}</li>
 *   <li>{@link psl.Task#getEffort <em>Effort</em>}</li>
 *   <li>{@link psl.Task#getDependencies <em>Dependencies</em>}</li>
 *   <li>{@link psl.Task#getDeliverables <em>Deliverables</em>}</li>
 * </ul>
 *
 * @see psl.PslPackage#getTask()
 * @model
 * @generated
 */
public interface Task extends EObject {
	/**
	 * Returns the value of the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Title</em>' attribute.
	 * @see #setTitle(String)
	 * @see psl.PslPackage#getTask_Title()
	 * @model
	 * @generated
	 */
	String getTitle();

	/**
	 * Sets the value of the '{@link psl.Task#getTitle <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Title</em>' attribute.
	 * @see #getTitle()
	 * @generated
	 */
	void setTitle(String value);

	/**
	 * Returns the value of the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start</em>' attribute.
	 * @see #setStart(int)
	 * @see psl.PslPackage#getTask_Start()
	 * @model
	 * @generated
	 */
	int getStart();

	/**
	 * Sets the value of the '{@link psl.Task#getStart <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start</em>' attribute.
	 * @see #getStart()
	 * @generated
	 */
	void setStart(int value);

	/**
	 * Returns the value of the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Duration</em>' attribute.
	 * @see #setDuration(int)
	 * @see psl.PslPackage#getTask_Duration()
	 * @model
	 * @generated
	 */
	int getDuration();

	/**
	 * Sets the value of the '{@link psl.Task#getDuration <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Duration</em>' attribute.
	 * @see #getDuration()
	 * @generated
	 */
	void setDuration(int value);

	/**
	 * Returns the value of the '<em><b>Effort</b></em>' containment reference list.
	 * The list contents are of type {@link psl.Effort}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Effort</em>' containment reference list.
	 * @see psl.PslPackage#getTask_Effort()
	 * @model containment="true"
	 * @generated
	 */
	EList<Effort> getEffort();

	/**
	 * Returns the value of the '<em><b>Dependencies</b></em>' reference list.
	 * The list contents are of type {@link psl.Task}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dependencies</em>' reference list.
	 * @see psl.PslPackage#getTask_Dependencies()
	 * @model
	 * @generated
	 */
	EList<Task> getDependencies();

	/**
	 * Returns the value of the '<em><b>Deliverables</b></em>' containment reference list.
	 * The list contents are of type {@link psl.Deliverable}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deliverables</em>' containment reference list.
	 * @see psl.PslPackage#getTask_Deliverables()
	 * @model containment="true"
	 * @generated
	 */
	EList<Deliverable> getDeliverables();

} // Task
