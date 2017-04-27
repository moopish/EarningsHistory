/**
 * <p>
 * === date package ===
 * </p><p>
 *  The <tt>date</tt> package contains all definitions related to the <tt>Date</tt> class which includes three enums:
 *  <ul>
 *      <li>{@link ehist.mem.date.Day} - for all functionality relating to a day and days of the week</li>
 *      <li>{@link ehist.mem.date.Month} - for all functionality relating to a month</li>
 *      <li>{@link ehist.mem.date.Year} - for all functionality relating to a year
 *      (note that this is an enum but has not enumerable values)</li>
 *  </ul>
 *  The package also includes some helpful classes and interfaces such as:
 *  <ul>
 *      <li>{@link ehist.mem.date.DateFormatter} - for formatting dates to readable output</li>
 *  </ul>
 * For creating <tt>Date</tt> instances in a managed way, use the
 * {@link ehist.mem.date.DateManager#getDate(int, int, int)} or
 * {@link ehist.mem.date.DateManager#getDate(int, ehist.mem.date.Month, int)} methods.
 * </p>
 *
 * @since EHist 1.0
 *
 * @author Michael van Dyk
 */
package ehist.mem.date;