/* 
 * Copyright (C) 2018 J. Alberdi-Rodriguez
 *
 * This file is part of Morphokinetics.
 *
 * Morphokinetics is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Morphokinetics is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Morphokinetics.  If not, see <http://www.gnu.org/licenses/>.
 */
package basic;

import java.util.EnumSet;
import java.util.Set;

/**
 * Evaluator type enumerator. Based on this class we can choose between different evaluators. Main
 * idea taken from:
 * http://stackoverflow.com/questions/5346477/implementing-a-bitfield-using-java-enums and
 * http://eddmann.com/posts/using-bit-flags-and-enumsets-in-java/
 *
 * @author J. Alberdi-Rodriguez
 */
public class EvaluatorType {

  public enum evaluatorFlag {
    PSD(1 << 0), TIME(1 << 1), HIERARCHY(1 << 2);

    public static final EnumSet<evaluatorFlag> ALL_OPTS = EnumSet.allOf(evaluatorFlag.class);
    private final long statusFlagValue;

    evaluatorFlag(long statusFlagValue) {
      this.statusFlagValue = statusFlagValue;
    }

    public long getStatusFlagValue() {
      return statusFlagValue;
    }
  }

  /**
   * Translates a numeric status code into a Set of StatusFlag enums
   *
   * @param statusValue statusValue
   * @return EnumSet representing a documents status
   */
  public EnumSet<evaluatorFlag> getStatusFlags(long statusValue) {
    EnumSet statusFlags = EnumSet.noneOf(evaluatorFlag.class);

    // Iterate all possible flag values and add them if the power of two is equal
    for (evaluatorFlag myFlag : evaluatorFlag.values()) {
      if ((myFlag.getStatusFlagValue() & statusValue) == myFlag.getStatusFlagValue()) {
        statusFlags.add(myFlag);
      }
    }

    return statusFlags;
  }

  /**
   * Translates a set of StatusFlag enums into a numeric status code
   *
   * @param flags if statusFlags
   * @return numeric representation of the document status
   */
  public long getStatusValue(Set<evaluatorFlag> flags) {
    long value = 0;
    for (evaluatorFlag myFlag : flags) {
      value |= myFlag.getStatusFlagValue();
    }
    return value;
  }

  public enum hierarchyFlag {
    GENERAL, STEP, FROM_REFERENCE_FROBENIUS, FROM_REFERENCE;

    public static final EnumSet<hierarchyFlag> ALL_OPTS = EnumSet.allOf(hierarchyFlag.class);
  }

}
