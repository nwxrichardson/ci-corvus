/**
 */
package psl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see psl.PslFactory
 * @model kind="package"
 * @generated
 */
public interface PslPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "psl";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "psl";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PslPackage eINSTANCE = psl.impl.PslPackageImpl.init();

	/**
	 * The meta object id for the '{@link psl.impl.ProjectImpl <em>Project</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see psl.impl.ProjectImpl
	 * @see psl.impl.PslPackageImpl#getProject()
	 * @generated
	 */
	int PROJECT = 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__TITLE = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Tasks</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__TASKS = 2;

	/**
	 * The feature id for the '<em><b>People</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT__PEOPLE = 3;

	/**
	 * The number of structural features of the '<em>Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Project</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROJECT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link psl.impl.TaskImpl <em>Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see psl.impl.TaskImpl
	 * @see psl.impl.PslPackageImpl#getTask()
	 * @generated
	 */
	int TASK = 1;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__TITLE = 0;

	/**
	 * The feature id for the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__START = 1;

	/**
	 * The feature id for the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__DURATION = 2;

	/**
	 * The feature id for the '<em><b>Effort</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__EFFORT = 3;

	/**
	 * The feature id for the '<em><b>Dependencies</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__DEPENDENCIES = 4;

	/**
	 * The feature id for the '<em><b>Deliverables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK__DELIVERABLES = 5;

	/**
	 * The number of structural features of the '<em>Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TASK_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link psl.impl.PersonImpl <em>Person</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see psl.impl.PersonImpl
	 * @see psl.impl.PslPackageImpl#getPerson()
	 * @generated
	 */
	int PERSON = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON__NAME = 0;

	/**
	 * The number of structural features of the '<em>Person</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Person</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERSON_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link psl.impl.EffortImpl <em>Effort</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see psl.impl.EffortImpl
	 * @see psl.impl.PslPackageImpl#getEffort()
	 * @generated
	 */
	int EFFORT = 3;

	/**
	 * The feature id for the '<em><b>Person</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EFFORT__PERSON = 0;

	/**
	 * The feature id for the '<em><b>Percentage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EFFORT__PERCENTAGE = 1;

	/**
	 * The number of structural features of the '<em>Effort</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EFFORT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Effort</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EFFORT_OPERATION_COUNT = 0;


	/**
	 * The meta object id for the '{@link psl.impl.DeliverableImpl <em>Deliverable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see psl.impl.DeliverableImpl
	 * @see psl.impl.PslPackageImpl#getDeliverable()
	 * @generated
	 */
	int DELIVERABLE = 4;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__TITLE = 0;

	/**
	 * The feature id for the '<em><b>Due</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__DUE = 1;

	/**
	 * The feature id for the '<em><b>Lead</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE__LEAD = 2;

	/**
	 * The number of structural features of the '<em>Deliverable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Deliverable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DELIVERABLE_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link psl.Project <em>Project</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Project</em>'.
	 * @see psl.Project
	 * @generated
	 */
	EClass getProject();

	/**
	 * Returns the meta object for the attribute '{@link psl.Project#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Title</em>'.
	 * @see psl.Project#getTitle()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_Title();

	/**
	 * Returns the meta object for the attribute '{@link psl.Project#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see psl.Project#getDescription()
	 * @see #getProject()
	 * @generated
	 */
	EAttribute getProject_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link psl.Project#getTasks <em>Tasks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tasks</em>'.
	 * @see psl.Project#getTasks()
	 * @see #getProject()
	 * @generated
	 */
	EReference getProject_Tasks();

	/**
	 * Returns the meta object for the containment reference list '{@link psl.Project#getPeople <em>People</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>People</em>'.
	 * @see psl.Project#getPeople()
	 * @see #getProject()
	 * @generated
	 */
	EReference getProject_People();

	/**
	 * Returns the meta object for class '{@link psl.Task <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Task</em>'.
	 * @see psl.Task
	 * @generated
	 */
	EClass getTask();

	/**
	 * Returns the meta object for the attribute '{@link psl.Task#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Title</em>'.
	 * @see psl.Task#getTitle()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Title();

	/**
	 * Returns the meta object for the attribute '{@link psl.Task#getStart <em>Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start</em>'.
	 * @see psl.Task#getStart()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Start();

	/**
	 * Returns the meta object for the attribute '{@link psl.Task#getDuration <em>Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duration</em>'.
	 * @see psl.Task#getDuration()
	 * @see #getTask()
	 * @generated
	 */
	EAttribute getTask_Duration();

	/**
	 * Returns the meta object for the containment reference list '{@link psl.Task#getEffort <em>Effort</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Effort</em>'.
	 * @see psl.Task#getEffort()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_Effort();

	/**
	 * Returns the meta object for the reference list '{@link psl.Task#getDependencies <em>Dependencies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Dependencies</em>'.
	 * @see psl.Task#getDependencies()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_Dependencies();

	/**
	 * Returns the meta object for the containment reference list '{@link psl.Task#getDeliverables <em>Deliverables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Deliverables</em>'.
	 * @see psl.Task#getDeliverables()
	 * @see #getTask()
	 * @generated
	 */
	EReference getTask_Deliverables();

	/**
	 * Returns the meta object for class '{@link psl.Person <em>Person</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Person</em>'.
	 * @see psl.Person
	 * @generated
	 */
	EClass getPerson();

	/**
	 * Returns the meta object for the attribute '{@link psl.Person#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see psl.Person#getName()
	 * @see #getPerson()
	 * @generated
	 */
	EAttribute getPerson_Name();

	/**
	 * Returns the meta object for class '{@link psl.Effort <em>Effort</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Effort</em>'.
	 * @see psl.Effort
	 * @generated
	 */
	EClass getEffort();

	/**
	 * Returns the meta object for the reference '{@link psl.Effort#getPerson <em>Person</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Person</em>'.
	 * @see psl.Effort#getPerson()
	 * @see #getEffort()
	 * @generated
	 */
	EReference getEffort_Person();

	/**
	 * Returns the meta object for the attribute '{@link psl.Effort#getPercentage <em>Percentage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Percentage</em>'.
	 * @see psl.Effort#getPercentage()
	 * @see #getEffort()
	 * @generated
	 */
	EAttribute getEffort_Percentage();

	/**
	 * Returns the meta object for class '{@link psl.Deliverable <em>Deliverable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Deliverable</em>'.
	 * @see psl.Deliverable
	 * @generated
	 */
	EClass getDeliverable();

	/**
	 * Returns the meta object for the attribute '{@link psl.Deliverable#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Title</em>'.
	 * @see psl.Deliverable#getTitle()
	 * @see #getDeliverable()
	 * @generated
	 */
	EAttribute getDeliverable_Title();

	/**
	 * Returns the meta object for the attribute '{@link psl.Deliverable#getDue <em>Due</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Due</em>'.
	 * @see psl.Deliverable#getDue()
	 * @see #getDeliverable()
	 * @generated
	 */
	EAttribute getDeliverable_Due();

	/**
	 * Returns the meta object for the reference '{@link psl.Deliverable#getLead <em>Lead</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Lead</em>'.
	 * @see psl.Deliverable#getLead()
	 * @see #getDeliverable()
	 * @generated
	 */
	EReference getDeliverable_Lead();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PslFactory getPslFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link psl.impl.ProjectImpl <em>Project</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see psl.impl.ProjectImpl
		 * @see psl.impl.PslPackageImpl#getProject()
		 * @generated
		 */
		EClass PROJECT = eINSTANCE.getProject();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROJECT__TITLE = eINSTANCE.getProject_Title();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROJECT__DESCRIPTION = eINSTANCE.getProject_Description();

		/**
		 * The meta object literal for the '<em><b>Tasks</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROJECT__TASKS = eINSTANCE.getProject_Tasks();

		/**
		 * The meta object literal for the '<em><b>People</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROJECT__PEOPLE = eINSTANCE.getProject_People();

		/**
		 * The meta object literal for the '{@link psl.impl.TaskImpl <em>Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see psl.impl.TaskImpl
		 * @see psl.impl.PslPackageImpl#getTask()
		 * @generated
		 */
		EClass TASK = eINSTANCE.getTask();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK__TITLE = eINSTANCE.getTask_Title();

		/**
		 * The meta object literal for the '<em><b>Start</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK__START = eINSTANCE.getTask_Start();

		/**
		 * The meta object literal for the '<em><b>Duration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TASK__DURATION = eINSTANCE.getTask_Duration();

		/**
		 * The meta object literal for the '<em><b>Effort</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK__EFFORT = eINSTANCE.getTask_Effort();

		/**
		 * The meta object literal for the '<em><b>Dependencies</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK__DEPENDENCIES = eINSTANCE.getTask_Dependencies();

		/**
		 * The meta object literal for the '<em><b>Deliverables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TASK__DELIVERABLES = eINSTANCE.getTask_Deliverables();

		/**
		 * The meta object literal for the '{@link psl.impl.PersonImpl <em>Person</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see psl.impl.PersonImpl
		 * @see psl.impl.PslPackageImpl#getPerson()
		 * @generated
		 */
		EClass PERSON = eINSTANCE.getPerson();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERSON__NAME = eINSTANCE.getPerson_Name();

		/**
		 * The meta object literal for the '{@link psl.impl.EffortImpl <em>Effort</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see psl.impl.EffortImpl
		 * @see psl.impl.PslPackageImpl#getEffort()
		 * @generated
		 */
		EClass EFFORT = eINSTANCE.getEffort();

		/**
		 * The meta object literal for the '<em><b>Person</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EFFORT__PERSON = eINSTANCE.getEffort_Person();

		/**
		 * The meta object literal for the '<em><b>Percentage</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EFFORT__PERCENTAGE = eINSTANCE.getEffort_Percentage();

		/**
		 * The meta object literal for the '{@link psl.impl.DeliverableImpl <em>Deliverable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see psl.impl.DeliverableImpl
		 * @see psl.impl.PslPackageImpl#getDeliverable()
		 * @generated
		 */
		EClass DELIVERABLE = eINSTANCE.getDeliverable();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELIVERABLE__TITLE = eINSTANCE.getDeliverable_Title();

		/**
		 * The meta object literal for the '<em><b>Due</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DELIVERABLE__DUE = eINSTANCE.getDeliverable_Due();

		/**
		 * The meta object literal for the '<em><b>Lead</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DELIVERABLE__LEAD = eINSTANCE.getDeliverable_Lead();

	}

} //PslPackage
