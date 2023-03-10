/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.Operands;
import com.jaspersoft.studio.data.sql.OrExpr;
import com.jaspersoft.studio.data.sql.SQLCaseOperand;
import com.jaspersoft.studio.data.sql.SQLCaseWhens;
import com.jaspersoft.studio.data.sql.SqlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SQL Case Operand</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.SQLCaseOperandImpl#getWop <em>Wop</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.SQLCaseOperandImpl#getExpr <em>Expr</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.SQLCaseOperandImpl#getWhen <em>When</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SQLCaseOperandImpl extends MinimalEObjectImpl.Container implements SQLCaseOperand
{
  /**
   * The cached value of the '{@link #getWop() <em>Wop</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWop()
   * @generated
   * @ordered
   */
  protected Operands wop;

  /**
   * The cached value of the '{@link #getExpr() <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpr()
   * @generated
   * @ordered
   */
  protected OrExpr expr;

  /**
   * The cached value of the '{@link #getWhen() <em>When</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWhen()
   * @generated
   * @ordered
   */
  protected SQLCaseWhens when;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SQLCaseOperandImpl()
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
    return SqlPackage.Literals.SQL_CASE_OPERAND;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Operands getWop()
  {
    return wop;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetWop(Operands newWop, NotificationChain msgs)
  {
    Operands oldWop = wop;
    wop = newWop;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.SQL_CASE_OPERAND__WOP, oldWop, newWop);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setWop(Operands newWop)
  {
    if (newWop != wop)
    {
      NotificationChain msgs = null;
      if (wop != null)
        msgs = ((InternalEObject)wop).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.SQL_CASE_OPERAND__WOP, null, msgs);
      if (newWop != null)
        msgs = ((InternalEObject)newWop).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.SQL_CASE_OPERAND__WOP, null, msgs);
      msgs = basicSetWop(newWop, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SQL_CASE_OPERAND__WOP, newWop, newWop));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public OrExpr getExpr()
  {
    return expr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExpr(OrExpr newExpr, NotificationChain msgs)
  {
    OrExpr oldExpr = expr;
    expr = newExpr;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.SQL_CASE_OPERAND__EXPR, oldExpr, newExpr);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setExpr(OrExpr newExpr)
  {
    if (newExpr != expr)
    {
      NotificationChain msgs = null;
      if (expr != null)
        msgs = ((InternalEObject)expr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.SQL_CASE_OPERAND__EXPR, null, msgs);
      if (newExpr != null)
        msgs = ((InternalEObject)newExpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.SQL_CASE_OPERAND__EXPR, null, msgs);
      msgs = basicSetExpr(newExpr, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SQL_CASE_OPERAND__EXPR, newExpr, newExpr));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public SQLCaseWhens getWhen()
  {
    return when;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetWhen(SQLCaseWhens newWhen, NotificationChain msgs)
  {
    SQLCaseWhens oldWhen = when;
    when = newWhen;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.SQL_CASE_OPERAND__WHEN, oldWhen, newWhen);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setWhen(SQLCaseWhens newWhen)
  {
    if (newWhen != when)
    {
      NotificationChain msgs = null;
      if (when != null)
        msgs = ((InternalEObject)when).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.SQL_CASE_OPERAND__WHEN, null, msgs);
      if (newWhen != null)
        msgs = ((InternalEObject)newWhen).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.SQL_CASE_OPERAND__WHEN, null, msgs);
      msgs = basicSetWhen(newWhen, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.SQL_CASE_OPERAND__WHEN, newWhen, newWhen));
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
      case SqlPackage.SQL_CASE_OPERAND__WOP:
        return basicSetWop(null, msgs);
      case SqlPackage.SQL_CASE_OPERAND__EXPR:
        return basicSetExpr(null, msgs);
      case SqlPackage.SQL_CASE_OPERAND__WHEN:
        return basicSetWhen(null, msgs);
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
      case SqlPackage.SQL_CASE_OPERAND__WOP:
        return getWop();
      case SqlPackage.SQL_CASE_OPERAND__EXPR:
        return getExpr();
      case SqlPackage.SQL_CASE_OPERAND__WHEN:
        return getWhen();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case SqlPackage.SQL_CASE_OPERAND__WOP:
        setWop((Operands)newValue);
        return;
      case SqlPackage.SQL_CASE_OPERAND__EXPR:
        setExpr((OrExpr)newValue);
        return;
      case SqlPackage.SQL_CASE_OPERAND__WHEN:
        setWhen((SQLCaseWhens)newValue);
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
      case SqlPackage.SQL_CASE_OPERAND__WOP:
        setWop((Operands)null);
        return;
      case SqlPackage.SQL_CASE_OPERAND__EXPR:
        setExpr((OrExpr)null);
        return;
      case SqlPackage.SQL_CASE_OPERAND__WHEN:
        setWhen((SQLCaseWhens)null);
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
      case SqlPackage.SQL_CASE_OPERAND__WOP:
        return wop != null;
      case SqlPackage.SQL_CASE_OPERAND__EXPR:
        return expr != null;
      case SqlPackage.SQL_CASE_OPERAND__WHEN:
        return when != null;
    }
    return super.eIsSet(featureID);
  }

} //SQLCaseOperandImpl
