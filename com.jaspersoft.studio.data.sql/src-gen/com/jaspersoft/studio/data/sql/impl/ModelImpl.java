/**
 */
package com.jaspersoft.studio.data.sql.impl;

import com.jaspersoft.studio.data.sql.Model;
import com.jaspersoft.studio.data.sql.SelectQuery;
import com.jaspersoft.studio.data.sql.SqlPackage;
import com.jaspersoft.studio.data.sql.WithQuery;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ModelImpl#getWq <em>Wq</em>}</li>
 *   <li>{@link com.jaspersoft.studio.data.sql.impl.ModelImpl#getQuery <em>Query</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ModelImpl extends MinimalEObjectImpl.Container implements Model
{
  /**
   * The cached value of the '{@link #getWq() <em>Wq</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWq()
   * @generated
   * @ordered
   */
  protected WithQuery wq;

  /**
   * The cached value of the '{@link #getQuery() <em>Query</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getQuery()
   * @generated
   * @ordered
   */
  protected SelectQuery query;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ModelImpl()
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
    return SqlPackage.Literals.MODEL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public WithQuery getWq()
  {
    return wq;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetWq(WithQuery newWq, NotificationChain msgs)
  {
    WithQuery oldWq = wq;
    wq = newWq;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.MODEL__WQ, oldWq, newWq);
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
  public void setWq(WithQuery newWq)
  {
    if (newWq != wq)
    {
      NotificationChain msgs = null;
      if (wq != null)
        msgs = ((InternalEObject)wq).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.MODEL__WQ, null, msgs);
      if (newWq != null)
        msgs = ((InternalEObject)newWq).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.MODEL__WQ, null, msgs);
      msgs = basicSetWq(newWq, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.MODEL__WQ, newWq, newWq));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public SelectQuery getQuery()
  {
    return query;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetQuery(SelectQuery newQuery, NotificationChain msgs)
  {
    SelectQuery oldQuery = query;
    query = newQuery;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SqlPackage.MODEL__QUERY, oldQuery, newQuery);
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
  public void setQuery(SelectQuery newQuery)
  {
    if (newQuery != query)
    {
      NotificationChain msgs = null;
      if (query != null)
        msgs = ((InternalEObject)query).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SqlPackage.MODEL__QUERY, null, msgs);
      if (newQuery != null)
        msgs = ((InternalEObject)newQuery).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SqlPackage.MODEL__QUERY, null, msgs);
      msgs = basicSetQuery(newQuery, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SqlPackage.MODEL__QUERY, newQuery, newQuery));
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
      case SqlPackage.MODEL__WQ:
        return basicSetWq(null, msgs);
      case SqlPackage.MODEL__QUERY:
        return basicSetQuery(null, msgs);
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
      case SqlPackage.MODEL__WQ:
        return getWq();
      case SqlPackage.MODEL__QUERY:
        return getQuery();
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
      case SqlPackage.MODEL__WQ:
        setWq((WithQuery)newValue);
        return;
      case SqlPackage.MODEL__QUERY:
        setQuery((SelectQuery)newValue);
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
      case SqlPackage.MODEL__WQ:
        setWq((WithQuery)null);
        return;
      case SqlPackage.MODEL__QUERY:
        setQuery((SelectQuery)null);
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
      case SqlPackage.MODEL__WQ:
        return wq != null;
      case SqlPackage.MODEL__QUERY:
        return query != null;
    }
    return super.eIsSet(featureID);
  }

} //ModelImpl
