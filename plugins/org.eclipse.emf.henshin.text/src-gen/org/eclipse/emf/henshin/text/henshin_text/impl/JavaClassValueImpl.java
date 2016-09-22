/**
 * generated by Xtext 2.10.0
 */
package org.eclipse.emf.henshin.text.henshin_text.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.henshin.text.henshin_text.Expression;
import org.eclipse.emf.henshin.text.henshin_text.Henshin_textPackage;
import org.eclipse.emf.henshin.text.henshin_text.JavaClassValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Java Class Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.henshin.text.henshin_text.impl.JavaClassValueImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.emf.henshin.text.henshin_text.impl.JavaClassValueImpl#getJavaParameter <em>Java Parameter</em>}</li>
 * </ul>
 *
 * @generated
 */
public class JavaClassValueImpl extends ExpressionImpl implements JavaClassValue
{
  /**
   * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected static final String VALUE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected String value = VALUE_EDEFAULT;

  /**
   * The cached value of the '{@link #getJavaParameter() <em>Java Parameter</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getJavaParameter()
   * @generated
   * @ordered
   */
  protected EList<Expression> javaParameter;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected JavaClassValueImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return Henshin_textPackage.Literals.JAVA_CLASS_VALUE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setValue(String newValue)
  {
    String oldValue = value;
    value = newValue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, Henshin_textPackage.JAVA_CLASS_VALUE__VALUE, oldValue, value));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Expression> getJavaParameter()
  {
    if (javaParameter == null)
    {
      javaParameter = new EObjectContainmentEList<Expression>(Expression.class, this, Henshin_textPackage.JAVA_CLASS_VALUE__JAVA_PARAMETER);
    }
    return javaParameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case Henshin_textPackage.JAVA_CLASS_VALUE__JAVA_PARAMETER:
        return ((InternalEList<?>)getJavaParameter()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case Henshin_textPackage.JAVA_CLASS_VALUE__VALUE:
        return getValue();
      case Henshin_textPackage.JAVA_CLASS_VALUE__JAVA_PARAMETER:
        return getJavaParameter();
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
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case Henshin_textPackage.JAVA_CLASS_VALUE__VALUE:
        setValue((String)newValue);
        return;
      case Henshin_textPackage.JAVA_CLASS_VALUE__JAVA_PARAMETER:
        getJavaParameter().clear();
        getJavaParameter().addAll((Collection<? extends Expression>)newValue);
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
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case Henshin_textPackage.JAVA_CLASS_VALUE__VALUE:
        setValue(VALUE_EDEFAULT);
        return;
      case Henshin_textPackage.JAVA_CLASS_VALUE__JAVA_PARAMETER:
        getJavaParameter().clear();
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
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case Henshin_textPackage.JAVA_CLASS_VALUE__VALUE:
        return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
      case Henshin_textPackage.JAVA_CLASS_VALUE__JAVA_PARAMETER:
        return javaParameter != null && !javaParameter.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (value: ");
    result.append(value);
    result.append(')');
    return result.toString();
  }

} //JavaClassValueImpl