/**
 * <p>
 * === date package ===
 * </p><p>
 *  The <tt>date</tt> package contains all definitions related to the <tt>Date</tt> class which includes three enums:
 *  <ul>
 *      <li>{@link ehist.data.date.Day} - for all functionality relating to a day and days of the week</li>
 *      <li>{@link ehist.data.date.Month} - for all functionality relating to a month</li>
 *      <li>{@link ehist.data.date.Year} - for all functionality relating to a year
 *      (note that this is an enum but has not enumerable values)</li>
 *  </ul>
 *  The package also includes some helpful classes and interfaces such as:
 *  <ul>
 *      <li>{@link ehist.data.date.DateFormatter} - for formatting dates to readable output</li>
 *  </ul>
 * For creating <tt>Date</tt> instances, use the
 * {@link ehist.data.date.Date#getDate(int, int, int)} or
 * {@link ehist.data.date.Date#getDate(int, ehist.data.date.Month, int)} methods.
 * </p>
 *
 * @since EHist 1.0
 *
 * @author Michael van Dyk
 */
package ehist.data.date;