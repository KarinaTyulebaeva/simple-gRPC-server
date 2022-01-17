package client
import io.grpc.{ManagedChannelBuilder, Status}
import register.{RegisterData, UserId}
import zio.{ExitCode, Has, Layer, URIO, ZIO}
import register.ZioRegister.RegisterServiceClient
import scalapb.zio_grpc.ZManagedChannel
import zio.console._
object Main extends zio.App {
  val clientLayer: Layer[Throwable, RegisterServiceClient] =
    RegisterServiceClient.live(
     ZManagedChannel(ManagedChannelBuilder.forAddress("localhost", 9000).usePlaintext())
    )

  def logic ={
    for{
      response<- RegisterServiceClient.signup(RegisterData("Karina", "123"))
      _ <- putStrLn(s"$response").orDie
    } yield ()
  }
  override def run(args: List[String]): URIO[zio.ZEnv, ExitCode] =
    logic.provideCustomLayer(clientLayer).exitCode
}
