package org.eclipse.emf.henshin.giraph.templates;

import java.util.*;

public class HenshinUtilTemplate
{
  protected static String nl;
  public static synchronized HenshinUtilTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    HenshinUtilTemplate result = new HenshinUtilTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = ";" + NL + "" + NL + "import java.io.ByteArrayOutputStream;" + NL + "import java.io.IOException;" + NL + "import java.nio.ByteBuffer;" + NL + "import java.nio.IntBuffer;" + NL + "import java.nio.LongBuffer;" + NL + "import java.util.Arrays;" + NL + "import java.util.ArrayList;" + NL + "import java.util.HashSet;" + NL + "import java.util.List;" + NL + "import java.util.LinkedHashSet;" + NL + "import java.util.Set;" + NL + "import java.util.UUID;" + NL + "" + NL + "import org.apache.giraph.aggregators.BasicAggregator;" + NL + "import org.apache.giraph.edge.Edge;" + NL + "import org.apache.giraph.edge.EdgeFactory;" + NL + "import org.apache.giraph.graph.Vertex;" + NL + "import org.apache.giraph.io.formats.TextVertexInputFormat;" + NL + "import org.apache.giraph.io.formats.TextVertexOutputFormat;" + NL + "import org.apache.hadoop.io.BytesWritable;" + NL + "import org.apache.hadoop.io.ByteWritable;" + NL + "import org.apache.hadoop.io.Text;" + NL + "import org.apache.hadoop.mapreduce.InputSplit;" + NL + "import org.apache.hadoop.mapreduce.TaskAttemptContext;" + NL + "import org.json.JSONArray;" + NL + "import org.json.JSONException;" + NL + "" + NL + "/**" + NL + " * Henshin utility classes and methods." + NL + " */" + NL + "public class HenshinUtil {" + NL + "" + NL + "\t/**" + NL + "\t * Length of integers in bytes." + NL + "\t */" + NL + "\tprivate static final int INT_LENGTH = Integer.SIZE / Byte.SIZE;" + NL + "" + NL + "\t/**" + NL + "\t * Private constructor." + NL + "\t */" + NL + "\tprivate HenshinUtil() {" + NL + "\t\t// Prevent instantiation" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Remove duplicate matches." + NL + "\t * @param matches List of matches." + NL + "\t * @return Filtered list." + NL + "\t */" + NL + "\tpublic static List<Match> removeDuplicateMatches(Iterable<Match> matches) {" + NL + "\t\tSet<Match> result = new LinkedHashSet<>();" + NL + "\t\tfor (Match m : matches) {" + NL + "\t\t\tresult.add(m);" + NL + "\t\t}" + NL + "\t\treturn new ArrayList<>(result);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Remove non-injective matches." + NL + "\t * @param matches List of matches." + NL + "\t * @return Filtered list." + NL + "\t */" + NL + "\tpublic static List<Match> removeNonInjectiveMatches(Iterable<Match> matches) {" + NL + "\t\tList<Match> result = new ArrayList<>();" + NL + "\t\tfor (Match m : matches) {" + NL + "\t\t\tif (m.isInjective()) {" + NL + "\t\t\t\tresult.add(m);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn result;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Henshin data." + NL + "\t */" + NL + "\tpublic abstract static class Bytes extends BytesWritable {" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Default constructor." + NL + "\t\t */" + NL + "\t\tpublic Bytes() {" + NL + "\t\t\tsuper();" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Extra constructor." + NL + "\t\t * @param data The data." + NL + "\t\t */" + NL + "\t\tpublic Bytes(byte[] data) {" + NL + "\t\t\tsuper(data);" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Set the size." + NL + "\t\t * @param size The new size." + NL + "\t\t */" + NL + "\t\t@Override" + NL + "\t\tpublic void setSize(int size) {" + NL + "\t\t\tif (size != getCapacity()) {" + NL + "\t\t\t\tsetCapacity(size);" + NL + "\t\t\t}" + NL + "\t\t\tsuper.setSize(size);" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Pretty-print this bytes object." + NL + "\t\t * @return The printed string." + NL + "\t\t */" + NL + "\t\t@Override" + NL + "\t\tpublic String toString() {" + NL + "\t\t\tbyte[] bytes = getBytes();" + NL + "\t\t\tStringBuffer result = new StringBuffer();" + NL + "\t\t\tfor (int i = 0; i < bytes.length; i++) {" + NL + "\t\t\t\tresult.append(bytes[i]);" + NL + "\t\t\t\tif (i < bytes.length - 1) {" + NL + "\t\t\t\t\tresult.append(\",\");" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t\treturn \"[\" + result + \"]\";" + NL + "\t\t}" + NL + "" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Henshin vertex ID." + NL + "\t */" + NL + "\tpublic static class VertexId extends Bytes {" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Default constructor." + NL + "\t\t */" + NL + "\t\tpublic VertexId() {" + NL + "\t\t\tsuper();" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Extra constructor." + NL + "\t\t * @param data The data." + NL + "\t\t */" + NL + "\t\tpublic VertexId(byte[] data) {" + NL + "\t\t\tsuper(data);" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Create a new random vertex ID." + NL + "\t\t * The vertex ID is derived from a random UUID." + NL + "\t\t * @return The new vertex ID." + NL + "\t\t */" + NL + "\t\tpublic static VertexId randomVertexId() {" + NL + "\t\t\tUUID uuid = UUID.randomUUID();" + NL + "\t\t\tbyte[] bytes = new byte[(Long.SIZE / Byte.SIZE) * 2];" + NL + "\t\t\tByteBuffer buffer = ByteBuffer.wrap(bytes);" + NL + "\t\t\tLongBuffer longBuffer = buffer.asLongBuffer();" + NL + "\t\t\tlongBuffer.put(new long[] {" + NL + "\t\t\t\tuuid.getMostSignificantBits()," + NL + "\t\t\t\tuuid.getLeastSignificantBits()" + NL + "\t\t\t});" + NL + "\t\t\treturn new VertexId(bytes);" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Create an extended version of this vertex ID." + NL + "\t\t * @param value The value to be appended to this vertex ID." + NL + "\t\t * @return The extended version of this vertex ID." + NL + "\t\t */" + NL + "\t\tpublic VertexId append(byte value) {" + NL + "\t\t\tbyte[] bytes = getBytes();" + NL + "\t\t\tbytes = Arrays.copyOf(bytes, bytes.length + 1);" + NL + "\t\t\tbytes[bytes.length - 1] = value;" + NL + "\t\t\treturn new VertexId(bytes);" + NL + "\t\t}" + NL + "" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Henshin match object." + NL + "\t */" + NL + "\tpublic static class Match extends Bytes {" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Number of bytes before match starts" + NL + "\t\t */" + NL + "\t\tprivate final int PREAMBLE = 5;" + NL + "\t\t" + NL + "\t\t/**" + NL + "\t\t * Microstep position" + NL + "\t\t */" + NL + "\t\t private final int MICROSTEP_POS = 4;" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Empty match." + NL + "\t\t */" + NL + "\t\tpublic static final Match EMPTY = new Match();" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Default constructor." + NL + "\t\t */" + NL + "\t\tpublic Match() {" + NL + "\t\t\tsuper();" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Extra constructor." + NL + "\t\t * @param segment The segment of this match." + NL + "\t\t */" + NL + "\t\tpublic Match(int segment, int microstep) {" + NL + "\t\t\tsuper(new byte[] {" + NL + "\t\t\t\t(byte) (segment >>> 24)," + NL + "\t\t\t\t(byte) (segment >>> 16)," + NL + "\t\t\t\t(byte) (segment >>> 8)," + NL + "\t\t\t\t(byte) segment," + NL + "\t\t\t\t(byte) microstep });" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Extra constructor." + NL + "\t\t * @param data The data." + NL + "\t\t */" + NL + "\t\tpublic Match(byte[] data) {" + NL + "\t\t\tsuper(data);" + NL + "\t\t}" + NL + "\t\t" + NL + "\t\tpublic byte getMicrostep(){" + NL + "\t\t\treturn getBytes()[MICROSTEP_POS];" + NL + "\t\t}" + NL + "\t\t" + NL + "\t\tpublic Match setMicrostep(byte microstep){" + NL + "\t\t\treturn setByte(microstep, MICROSTEP_POS);" + NL + "\t\t}" + NL + "\t\t" + NL + "\t\tprivate Match setByte(byte data, int position){" + NL + "\t\t\tbyte[] bytes = getBytes();" + NL + "\t\t\tbyte[] result = Arrays.copyOf(bytes, bytes.length);" + NL + "\t\t\tresult[position] = data;" + NL + "\t\t\treturn new Match(result);" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Get the segment of this match." + NL + "\t\t * @return The segment." + NL + "\t\t */" + NL + "\t\tpublic int getSegment() {" + NL + "\t\t\tbyte[] bytes = getBytes();" + NL + "\t\t\treturn bytes[0] << 24 |" + NL + "\t\t\t\t(bytes[1] & 0xFF) << 16 |" + NL + "\t\t\t\t(bytes[2] & 0xFF) << 8 |" + NL + "\t\t\t\t(bytes[3] & 0xFF);" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Get the size of this match." + NL + "\t\t * @return The match size." + NL + "\t\t */" + NL + "\t\tpublic int getMatchSize() {" + NL + "\t\t\tbyte[] bytes = getBytes();" + NL + "\t\t\tint d = PREAMBLE;" + NL + "\t\t\tint size = 0;" + NL + "\t\t\twhile (d < bytes.length) {" + NL + "\t\t\t\td += bytes[d] + 1;" + NL + "\t\t\t\tsize++;" + NL + "\t\t\t}" + NL + "\t\t\treturn size;" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Get the vertex ID of a matched node." + NL + "\t\t * @param vertexIndex Index of the next vertex." + NL + "\t\t * @return The vertex ID." + NL + "\t\t */" + NL + "\t\tpublic VertexId getVertexId(int vertexIndex) {" + NL + "\t\t\tbyte[] bytes = getBytes();" + NL + "\t\t\tint d = PREAMBLE;" + NL + "\t\t\tfor (int i = 0; i < vertexIndex; i++) {" + NL + "\t\t\t\tif (d >= bytes.length) {" + NL + "\t\t\t\t\treturn null;" + NL + "\t\t\t\t}" + NL + "\t\t\t\td += bytes[d] + 1;" + NL + "\t\t\t}" + NL + "\t\t\tif (d >= bytes.length) {" + NL + "\t\t\t\treturn null;" + NL + "\t\t\t}" + NL + "\t\t\treturn new VertexId(" + NL + "\t\t\t\tArrays.copyOfRange(bytes, d + 1, d + 1 + bytes[d]));" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Get the index of a vertex ID of a matched node." + NL + "\t\t * @param vertexId A vertex ID." + NL + "\t\t * @return The index of the vertex ID or -1." + NL + "\t\t */" + NL + "\t\tpublic int indexOf(VertexId vertexId) {" + NL + "\t\t\tint i = 0;" + NL + "\t\t\tVertexId id;" + NL + "\t\t\tdo {" + NL + "\t\t\t\tid = getVertexId(i);" + NL + "\t\t\t\tif (vertexId.equals(id)) {" + NL + "\t\t\t\t\treturn i;" + NL + "\t\t\t\t}" + NL + "\t\t\t\ti++;" + NL + "\t\t\t} while (id != null);" + NL + "\t\t\treturn -1;" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Returns true if this match is injective." + NL + "\t\t * @return true if this match is injective." + NL + "\t\t */" + NL + "\t\tpublic boolean isInjective() {" + NL + "\t\t\tSet<VertexId> ids = new HashSet<>();" + NL + "\t\t\tint i = 0;" + NL + "\t\t\tVertexId id;" + NL + "\t\t\tdo {" + NL + "\t\t\t\tid = getVertexId(i++);" + NL + "\t\t\t\tif (id != null && !ids.add(id)) {" + NL + "\t\t\t\t\treturn false;" + NL + "\t\t\t\t}" + NL + "\t\t\t} while (id != null);" + NL + "\t\t\treturn true;" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Create an extended version of this (partial) match." + NL + "\t\t * @param vertexId The ID of the next matched vertex." + NL + "\t\t * @return The extended match object." + NL + "\t\t */" + NL + "\t\tpublic Match append(VertexId vertexId) {" + NL + "\t\t\tbyte[] bytes = getBytes();" + NL + "\t\t\tbyte[] id = vertexId.getBytes();" + NL + "\t\t\tbyte[] result = Arrays.copyOf(bytes, bytes.length + 1 + id.length);" + NL + "\t\t\tresult[bytes.length] = (byte) id.length;" + NL + "\t\t\tSystem.arraycopy(id, 0, result, bytes.length + 1, id.length);" + NL + "\t\t\treturn new Match(result);" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Create an extended version of this (partial) match." + NL + "\t\t * @param match Another partial match for the next matched vertices." + NL + "\t\t * @return The extended match object." + NL + "\t\t */" + NL + "\t\tpublic Match append(Match match) {" + NL + "\t\t\tbyte[] bytes1 = getBytes();" + NL + "\t\t\tbyte[] bytes2 = match.getBytes();" + NL + "\t\t\tbytes1 = Arrays.copyOf(bytes1, bytes1.length + bytes2.length - PREAMBLE);" + NL + "\t\t\tSystem.arraycopy(bytes2, PREAMBLE," + NL + "\t\t\t\tbytes1, bytes1.length - bytes2.length + PREAMBLE, bytes2.length - PREAMBLE);" + NL + "\t\t\treturn new Match(bytes1);" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Remove a vertex ID of a matched node." + NL + "\t\t * @param vertexIndex Index of the vertex ID." + NL + "\t\t * @return The new match." + NL + "\t\t */" + NL + "\t\tpublic Match remove(int vertexIndex) {" + NL + "\t\t\tbyte[] bytes = getBytes();" + NL + "\t\t\tint d = PREAMBLE;" + NL + "\t\t\tfor (int i = 0; i < vertexIndex; i++) {" + NL + "\t\t\t\tif (d >= bytes.length) {" + NL + "\t\t\t\t\treturn null;" + NL + "\t\t\t\t}" + NL + "\t\t\t\td += bytes[d] + 1;" + NL + "\t\t\t}" + NL + "\t\t\tif (d >= bytes.length) {" + NL + "\t\t\t\treturn null;" + NL + "\t\t\t}" + NL + "\t\t\tbyte[] result = Arrays.copyOf(bytes, bytes.length - bytes[d] - 1);" + NL + "\t\t\tif (d < result.length) {" + NL + "\t\t\t\tSystem.arraycopy(bytes, d + 1 + bytes[d]," + NL + "\t\t\t\t\tresult, d, result.length - d);" + NL + "\t\t\t}" + NL + "\t\t\treturn new Match(result);" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Create a copy of this match." + NL + "\t\t * @return The copy." + NL + "\t\t */" + NL + "\t\tpublic Match copy() {" + NL + "\t\t\treturn new Match(getBytes());" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Pretty-print this match." + NL + "\t\t * @return The printed string." + NL + "\t\t */" + NL + "\t\t@Override" + NL + "\t\tpublic String toString() {" + NL + "\t\t\tbyte[] bytes = getBytes();" + NL + "\t\t\tStringBuffer result = new StringBuffer();" + NL + "\t\t\tint i = PREAMBLE;" + NL + "\t\t\twhile (i < bytes.length) {" + NL + "\t\t\t\tint len = bytes[i++];" + NL + "\t\t\t\tresult.append(\"[\");" + NL + "\t\t\t\tfor (int j = 0; j < len; j++) {" + NL + "\t\t\t\t\tresult.append(bytes[i + j]);" + NL + "\t\t\t\t\tif (j < len - 1) {" + NL + "\t\t\t\t\t\tresult.append(\",\");" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t}" + NL;
  protected final String TEXT_3 = "\t\t\t\tresult.append(\"]\");" + NL + "\t\t\t\ti += len;" + NL + "\t\t\t\tif (i < bytes.length - 1) {" + NL + "\t\t\t\t\tresult.append(\",\");" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t\tif (bytes.length > 0) {" + NL + "\t\t\t\treturn \"s:\" + getSegment() + \",m:\" + getMicrostep() + \":[\" + result + \"]\";" + NL + "\t\t\t} else {" + NL + "\t\t\t\treturn \"[\" + result + \"]\";" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Henshin application stack." + NL + "\t */" + NL + "\tpublic static class ApplicationStack extends Bytes {" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Default constructor." + NL + "\t\t */" + NL + "\t\tpublic ApplicationStack() {" + NL + "\t\t\tsuper();" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Extra constructor." + NL + "\t\t * @param data The data." + NL + "\t\t */" + NL + "\t\tpublic ApplicationStack(byte[] data) {" + NL + "\t\t\tsuper(data);" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Get the size of this application stack." + NL + "\t\t * @return the size of this application stack." + NL + "\t\t */" + NL + "\t\tpublic int getStackSize() {" + NL + "\t\t\treturn (getBytes().length / INT_LENGTH) / 3;" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Get the unit index at an absolute position." + NL + "\t\t * @param position An absolute position in the stack." + NL + "\t\t * @return the unit index or -1." + NL + "\t\t */" + NL + "\t\tpublic int getUnit(int position) {" + NL + "\t\t\tIntBuffer intBuf = ByteBuffer.wrap(getBytes()).asIntBuffer();" + NL + "\t\t\tif (position < 0 || position * 3 >= intBuf.limit()) {" + NL + "\t\t\t\treturn -1;" + NL + "\t\t\t}" + NL + "\t\t\treturn intBuf.get(position * 3);" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Get the segment index at an absolute position." + NL + "\t\t * @param position An absolute position in the stack." + NL + "\t\t * @return the segment index or -1." + NL + "\t\t */" + NL + "\t\tpublic int getSegment(int position) {" + NL + "\t\t\tIntBuffer intBuf = ByteBuffer.wrap(getBytes()).asIntBuffer();" + NL + "\t\t\tif (position < 0 || (position * 3) + 1 >= intBuf.limit()) {" + NL + "\t\t\t\treturn -1;" + NL + "\t\t\t}" + NL + "\t\t\treturn intBuf.get((position * 3) + 1);" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Get the microstep at an absolute position." + NL + "\t\t * @param position An absolute position in the stack." + NL + "\t\t * @return the microstp or -1." + NL + "\t\t */" + NL + "\t\tpublic int getMicrostep(int position) {" + NL + "\t\t\tIntBuffer intBuf = ByteBuffer.wrap(getBytes()).asIntBuffer();" + NL + "\t\t\tif (position < 0 || (position * 3) + 2 >= intBuf.limit()) {" + NL + "\t\t\t\treturn -1;" + NL + "\t\t\t}" + NL + "\t\t\treturn intBuf.get((position * 3) + 2);" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Get the unit index at the last position." + NL + "\t\t * @return the unit index or -1." + NL + "\t\t */" + NL + "\t\tpublic int getLastUnit() {" + NL + "\t\t\treturn getUnit(getStackSize() - 1);" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Get the segment index at the last position." + NL + "\t\t * @return the segment index or -1." + NL + "\t\t */" + NL + "\t\tpublic int getLastSegment() {" + NL + "\t\t\treturn getSegment(getStackSize() - 1);" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Get the microstep at the last position." + NL + "\t\t * @return the microstep or -1." + NL + "\t\t */" + NL + "\t\tpublic int getLastMicrostep() {" + NL + "\t\t\treturn getMicrostep(getStackSize() - 1);" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Create an extended version of this application stack." + NL + "\t\t * @param unit The new unit index." + NL + "\t\t * @param segment The new segment index." + NL + "\t\t * @param microstep The new microstep." + NL + "\t\t * @return The extended version of this application stack." + NL + "\t\t */" + NL + "\t\tpublic ApplicationStack append(int unit, int segment, int microstep) {" + NL + "\t\t\tbyte[] bytes = getBytes();" + NL + "\t\t\tbytes = Arrays.copyOf(bytes, bytes.length + (INT_LENGTH * 3));" + NL + "\t\t\tIntBuffer intBuffer = ByteBuffer.wrap(bytes).asIntBuffer();" + NL + "\t\t\tintBuffer.put((bytes.length / INT_LENGTH) - 3, unit);" + NL + "\t\t\tintBuffer.put((bytes.length / INT_LENGTH) - 2, segment);" + NL + "\t\t\tintBuffer.put((bytes.length / INT_LENGTH) - 1, microstep);" + NL + "\t\t\treturn new ApplicationStack(bytes);" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Create a new version of this application stack without the last entry." + NL + "\t\t * @return The new version of this application stack." + NL + "\t\t */" + NL + "\t\tpublic ApplicationStack removeLast() {" + NL + "\t\t\tbyte[] bytes = getBytes();" + NL + "\t\t\tbytes = Arrays.copyOf(bytes," + NL + "\t\t\t\tMath.max(0, bytes.length - (INT_LENGTH * 3)));" + NL + "\t\t\treturn new ApplicationStack(bytes);" + NL + "\t\t}" + NL + "" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Aggregator class for application stacks." + NL + "\t */" + NL + "\tpublic static class ApplicationStackAggregator extends" + NL + "\t\tBasicAggregator<ApplicationStack> {" + NL + "" + NL + "\t\t@Override" + NL + "\t\tpublic void aggregate(ApplicationStack stack) {" + NL + "\t\t\t// no action" + NL + "\t\t}" + NL + "" + NL + "\t\t@Override" + NL + "\t\tpublic ApplicationStack createInitialValue() {" + NL + "\t\t\treturn new ApplicationStack();" + NL + "\t\t}" + NL + "" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Henshin input format." + NL + "\t */" + NL + "\tpublic static class InputFormat extends" + NL + "\t\tTextVertexInputFormat<VertexId, ByteWritable, ByteWritable> {" + NL + "" + NL + "\t\t@Override" + NL + "\t\tpublic TextVertexReader createVertexReader(InputSplit split," + NL + "\t\t\tTaskAttemptContext context) {" + NL + "\t\t\treturn new InputReader();" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Henshin input reader." + NL + "\t\t */" + NL + "\t\tclass InputReader extends" + NL + "\t\t\tTextVertexReaderFromEachLineProcessedHandlingExceptions<JSONArray," + NL + "\t\t\t\tJSONException> {" + NL + "" + NL + "\t\t\t@Override" + NL + "\t\t\tprotected JSONArray preprocessLine(Text line) throws JSONException {" + NL + "\t\t\t\treturn new JSONArray(line.toString());" + NL + "\t\t\t}" + NL + "" + NL + "\t\t\t@Override" + NL + "\t\t\tprotected VertexId getId(JSONArray jsonVertex)" + NL + "\t\t\t\tthrows JSONException, IOException {" + NL + "\t\t\t\treturn jsonArrayToVertexId(jsonVertex.getJSONArray(0));" + NL + "\t\t\t}" + NL + "" + NL + "\t\t\t/**" + NL + "\t\t\t * Convert a JSON array to a VertexId object." + NL + "\t\t\t * @param jsonArray The JSON array to be converted." + NL + "\t\t\t * @return The corresponding VertexId." + NL + "\t\t\t */" + NL + "\t\t\tprivate VertexId jsonArrayToVertexId(JSONArray jsonArray)" + NL + "\t\t\t\tthrows JSONException {" + NL + "\t\t\t\tByteArrayOutputStream out = new ByteArrayOutputStream();" + NL + "\t\t\t\tfor (int i = 0; i < jsonArray.length(); i++) {" + NL + "\t\t\t\t\tint value = jsonArray.getInt(i);" + NL + "\t\t\t\t\tif (value < 256) {" + NL + "\t\t\t\t\t\tout.write(value);" + NL + "\t\t\t\t\t} else {" + NL + "\t\t\t\t\t\tout.write(value >> 24);" + NL + "\t\t\t\t\t\tout.write(value >> 16);" + NL + "\t\t\t\t\t\tout.write(value >> 8);" + NL + "\t\t\t\t\t\tout.write(value);" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t}" + NL + "\t\t\t\treturn new VertexId(out.toByteArray());" + NL + "\t\t\t}" + NL + "" + NL + "\t\t\t@Override" + NL + "\t\t\tprotected ByteWritable getValue(JSONArray jsonVertex)" + NL + "\t\t\t\tthrows JSONException, IOException {" + NL + "\t\t\t\treturn new ByteWritable((byte) jsonVertex.getInt(1));" + NL + "\t\t\t}" + NL + "" + NL + "\t\t\t@Override" + NL + "\t\t\tprotected Iterable<Edge<VertexId, ByteWritable>> getEdges(" + NL + "\t\t\t\tJSONArray jsonVertex) throws JSONException, IOException {" + NL + "\t\t\t\tJSONArray jsonEdgeArray = jsonVertex.getJSONArray(2);" + NL + "\t\t\t\tList<Edge<VertexId, ByteWritable>> edges =" + NL + "\t\t\t\t\tnew ArrayList<>(jsonEdgeArray.length());" + NL + "\t\t\t\tfor (int i = 0; i < jsonEdgeArray.length(); ++i) {" + NL + "\t\t\t\t\tJSONArray jsonEdge = jsonEdgeArray.getJSONArray(i);" + NL + "\t\t\t\t\tedges.add(EdgeFactory.create(" + NL + "\t\t\t\t\t\tjsonArrayToVertexId(jsonEdge.getJSONArray(0))," + NL + "\t\t\t\t\t\tnew ByteWritable((byte) jsonEdge.getInt(1))));" + NL + "\t\t\t\t}" + NL + "\t\t\t\treturn edges;" + NL + "\t\t\t}" + NL + "" + NL + "\t\t\t@Override" + NL + "\t\t\tprotected Vertex<VertexId, ByteWritable, ByteWritable>" + NL + "\t\t\thandleException(Text line, JSONArray jsonVertex, JSONException e) {" + NL + "\t\t\t\tthrow new IllegalArgumentException(" + NL + "\t\t\t\t\t\"Couldn't get vertex from line \" + line, e);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Henshin output format." + NL + "\t */" + NL + "\tpublic static class OutputFormat extends" + NL + "\t\tTextVertexOutputFormat<VertexId, ByteWritable, ByteWritable> {" + NL + "" + NL + "\t\t@Override" + NL + "\t\tpublic TextVertexWriter createVertexWriter(TaskAttemptContext context)" + NL + "\t\t\tthrows IOException, InterruptedException {" + NL + "\t\t\treturn new OutputWriter();" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * Henshin output writer." + NL + "\t\t */" + NL + "\t\tclass OutputWriter extends TextVertexWriterToEachLine {" + NL + "" + NL + "\t\t\t@Override" + NL + "\t\t\tprotected Text convertVertexToLine(" + NL + "\t\t\t\tVertex<VertexId, ByteWritable, ByteWritable> vertex)" + NL + "\t\t\t\tthrows IOException {" + NL + "" + NL + "\t\t\t\tJSONArray vertexArray = new JSONArray();" + NL + "\t\t\t\tJSONArray idArray = new JSONArray();" + NL + "\t\t\t\tbyte[] id = vertex.getId().getBytes();" + NL + "\t\t\t\tfor (int i = 0; i < id.length; i++) {" + NL + "\t\t\t\t\tidArray.put(id[i]);" + NL + "\t\t\t\t}" + NL + "\t\t\t\tvertexArray.put(idArray);" + NL + "\t\t\t\tvertexArray.put(vertex.getValue().get());" + NL + "\t\t\t\tJSONArray allEdgesArray = new JSONArray();" + NL + "\t\t\t\tfor (Edge<VertexId, ByteWritable> edge : vertex.getEdges()) {" + NL + "\t\t\t\t\tJSONArray edgeArray = new JSONArray();" + NL + "\t\t\t\t\tJSONArray targetIdArray = new JSONArray();" + NL + "\t\t\t\t\tbyte[] targetId = edge.getTargetVertexId().getBytes();" + NL + "\t\t\t\t\tfor (int i = 0; i < targetId.length; i++) {" + NL + "\t\t\t\t\t\ttargetIdArray.put(targetId[i]);" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t\tedgeArray.put(targetIdArray);" + NL + "\t\t\t\t\tedgeArray.put(edge.getValue().get());" + NL + "\t\t\t\t\tallEdgesArray.put(edgeArray);" + NL + "\t\t\t\t}" + NL + "\t\t\t\tvertexArray.put(allEdgesArray);" + NL + "\t\t\t\treturn new Text(vertexArray.toString());" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "}";
  protected final String TEXT_4 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    

@SuppressWarnings("unchecked")
Map<String,Object> args = (Map<String,Object>) argument;

String packageName = (String) args.get("packageName");
//boolean logging = (Boolean) args.get("logging");


    stringBuffer.append(TEXT_1);
    stringBuffer.append( packageName );
    stringBuffer.append(TEXT_2);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(TEXT_4);
    return stringBuffer.toString();
  }
}
