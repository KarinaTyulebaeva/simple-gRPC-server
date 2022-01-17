import io.grpc.Status
import register.{RegisterData, UUID, UserId}
import register.ZioRegister.{RRegisterService, RegisterService}
import register.ZioRegister.ZRegisterService.genericBindable
import scalapb.zio_grpc.{ServerMain, ServiceList}
import zio.console.{Console, putStrLn}
import zio.random.{Random, nextLong, nextLongBetween}
import zio.{ZEnv, ZIO}


class RegisterServiceImpl extends RRegisterService[Console with Random] {
  override def signup(request: RegisterData): ZIO[Console with Random, Status, UserId] =
    for{
      _ <- putStrLn(s"${request.login}").orDie
      id <- nextLongBetween(1L, Long.MaxValue)
    } yield UserId(Some(UUID(id)))
    }

object Main extends ServerMain{
  override def services: ServiceList[zio.ZEnv] = ServiceList.add(new RegisterServiceImpl)
}
