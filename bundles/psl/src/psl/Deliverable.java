/**
 */
package psl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Deliverable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link psl.Deliverable#getTitle <em>Title</em>}</li>
 *   <li>{@link psl.Deliverable#getDue <em>Due</em>}</li>
 *   <li>{@link psl.Deliverable#getLead <em>Lead</em>}</li>
 * </ul>
 *
 * @see psl.PslPackage#getDeliverable()
 * @model
 * @generated
 */
public interface Deliverable extends EObject {
	/**
	 * Returns the value of the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Title</em>' attribute.
	 * @see #setTitle(String)
	 * @see psl.PslPackage#getDeliverable_Title()
	 * @model
	 * @generated
	 */
	String getTitle();

	/**
	 * Sets the value of the '{@link psl.Deliverable#getTitle <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Title</em>' attribute.
	 * @see #getTitle()
	 * @generated
	 */
	void setTitle(String value);

	/**
	 * Returns the value of the '<em><b>Due</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Due</em>' attribute.
	 * @see #setDue(int)
	 * @see psl.PslPackage#getDeliverable_Due()
	 * @model
	 * @generated
	 */
	int getDue();

	/**
	 * Sets the value of the '{@link psl.Deliverable#getDue <em>Due</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Due</em>' attribute.
	 * @see #getDue()
	 * @generated
	 */
	void setDue(int value);

	/**
	 * Returns the value of the '<em><b>Lead</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lead</em>' reference.
	 * @see #setLead(Person)
	 * @see psl.PslPackage#getDeliverable_Lead()
	 * @model
	 * @generated
	 */
	Person getLead();

	/**
	 * Sets the value of the '{@link psl.Deliverable#getLead <em>Lead</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lead</em>' reference.
	 * @see #getLead()
	 * @generated
	 */
	void setLead(Person value);

} // Deliverable
