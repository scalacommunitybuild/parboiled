/*
 * Copyright (C) 2009-2011 Mathias Doenitz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.parboiled;

import org.parboiled.annotations.BuildParseTree;
import org.parboiled.test.TestNgParboiledTest;
import org.testng.annotations.Test;

public class SplitParserTest extends TestNgParboiledTest<Object> {

    @BuildParseTree
    public static class Parser extends BaseParser<Object> {
        final Primitives primitives = Parboiled.createParser(Primitives.class);

        public Rule Clause() {
            return Sequence(
                    primitives.Digit(),
                    primitives.Operator(),
                    primitives.Digit(),
                    EOI
            );
        }
    }

    @BuildParseTree
    public static class Primitives extends BaseParser<Object> {

        public Rule Operator() {
            return FirstOf('+', '-');
        }

        public Rule Digit() {
            return CharRange('0', '9');
        }

    }

    @Test
    public void test() {
        Parser parser = Parboiled.createParser(Parser.class);
        test(parser.Clause(), "1+5")
                .hasNoErrors()
                .hasParseTree("" +
                        "[Clause] '1+5'\n" +
                        "  [Digit] '1'\n" +
                        "  [Operator] '+'\n" +
                        "    ['+'] '+'\n" +
                        "  [Digit] '5'\n" +
                        "  [EOI]\n");
    }

}
