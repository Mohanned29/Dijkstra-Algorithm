public class Vertex implements Comparable<Vertex> {
  int id;
  int distance;

  public Vertex(int id, int distance) {
      this.id = id;
      this.distance = distance;
  }

  @Override
  public int compareTo(Vertex other) {
      return Integer.compare(this.distance, other.distance);
  }
}
