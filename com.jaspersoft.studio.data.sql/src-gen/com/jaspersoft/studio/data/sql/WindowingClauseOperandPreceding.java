/**
 */
package com.jaspersoft.studio.data.sql;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Windowing Clause Operand Preceding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.jaspersoft.studio.data.sql.WindowingClauseOperandPreceding#getExpr <em>Expr</em>}</li>
 * </ul>
 *
 * @see com.jaspersoft.studio.data.sql.SqlPackage#getWindowingClauseOperandPreceding()
 * @model
 * @generated
 */
public interface WindowingClauseOperandPreceding extends WindowingClause
{
  /**
   * Returns the value of the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expr</em>' containment reference.
   * @see #setExpr(AnalyticExprArg)
   * @see com.jaspersoft.studio.data.sql.SqlPackage#getWindowingClauseOperandPreceding_Expr()
   * @model containment="true"
   * @generated
   */
  AnalyticExprArg getExpr();

  /**
   * Sets the value of the '{@link com.jaspersoft.studio.data.sql.WindowingClauseOperandPreceding#getExpr <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expr</em>' containment reference.
   * @see #getExpr()
   * @generated
   */
  void setExpr(AnalyticExprArg value);

} // WindowingClauseOperandPreceding
